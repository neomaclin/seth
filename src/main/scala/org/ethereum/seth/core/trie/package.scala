package org.ethereum.seth.core

import cats._
import cats.implicits._
import org.ethereum.seth.core.math.HexPrefix
import org.ethereum.seth.core.rlp._


import scala.annotation.tailrec

package object trie {

  sealed trait Node[+V]

  final class NodeKey(input: Array[Byte], sign: Boolean) {
    val compacted = HexPrefix.encode(input, sign)
    def bytes = HexPrefix.decode(compacted)
    override def toString = compacted.hexString
  }

  case object EmptyNode extends Node[Nothing]

  final case class Leaf[+V](key: NodeKey, value: V) extends Node[V]

  final case class Extension[+V](key: NodeKey, node: Node[V]) extends Node[V]

  final case class Branch[+V](nodes: Vector[Node[V]] = Vector.fill[Node[V]](16) {EmptyNode},
                              value: Option[V]) extends Node[V]


  def nodeEncodable[V](implicit ev: RLPEncodable[V]): RLPEncodable[Node[V]] = new RLPEncodable[Node[V]] {
    override def from(node: Node[V]): RLPItem = node match {
      case EmptyNode => RLPLzero
      case Branch(nodes, value) => nodes.map(x=>from(x)) ++ value.map(rlp.encode(_)).map(toRLPItem)
      case Leaf(oldKey, value) => rlp.encode(oldKey.bytes) ++ rlp.encode(value)
      case Extension(oldKey, subNode) => Seq(toRLPItem(rlp.encode(oldKey.bytes)), from(subNode))
    }
  }

  implicit val StringNode =  nodeEncodable[String]
  implicit val bigIntNode =  nodeEncodable[BigInt]
  implicit val arrayNode =  nodeEncodable[Array[Byte]]
  implicit val longNode =  nodeEncodable[Long]
  implicit val intNode =  nodeEncodable[Int]
  implicit val byteNode =  nodeEncodable[Byte]


  private [trie] def putAll[V](onNode: Node[V], entries: Seq[(Array[Byte], V)]): Node[V] = {
    def putKV[V](node: Node[V], entry: (Array[Byte], V)): Node[V] = put(entry._1, entry._2, node)

    Foldable[List].foldLeft(entries.toList, onNode)(putKV[V])
  }

  private [trie] def dump[V](fromNode: Node[V]): Seq[(Array[Byte], V)] = {

    def scan(prefix: Array[Byte], node: Node[V], acc: List[(Array[Byte], V)]): List[(Array[Byte], V)] = {
      node match {
        case EmptyNode => acc
        case Branch(nodes, value) =>
          nodes
            .zipWithIndex
            .foldLeft(value.map((prefix, _)).toList ++ acc){ case (lx,(subNode, idx)) =>
              if (subNode == EmptyNode) lx else scan(prefix :+ idx.toByte, subNode, lx)
            }
        case Leaf(oldKey, value) => (prefix ++ oldKey.bytes, value) +: acc
        case Extension(oldKey, subNode) => scan(prefix ++ oldKey.bytes, subNode, acc)
      }
    }

    scan(Array.empty[Byte], fromNode, Nil)
  }

  @tailrec
  private [trie] def get[V](key: Array[Byte], fromNode: Node[V]): Option[V] ={
    fromNode match {
      case EmptyNode =>
        None
      case Branch(nodes, value) =>
        if (key.isEmpty) value else get(key.tail, nodes(key.head))
      case Leaf(oldKey, value) =>
        if (key sameElements oldKey.bytes) Some(value) else None
      case Extension(oldKey, subNode) =>
        if (key.startsWith(oldKey.bytes)) get(key.drop(oldKey.bytes.length), subNode) else None
    }
  }

  private [trie] def removeAll[V](keys: List[Array[Byte]], fromNode: Node[V]): Node[V] = {
    Foldable[List].foldLeft(keys, fromNode)((node, key) => remove[V](key, node))
  }

  private [trie] def remove[V](key: Array[Byte], fromNode: Node[V]): Node[V] = {
    fromNode match {
      case EmptyNode => EmptyNode
      case branch@Branch(nodes, _) =>
        val newBranch =
          if (key.isEmpty) branch.copy(value = None)
          else branch.copy(nodes = nodes.updated(index = key.head, elem = remove(key.tail, nodes(key.head))))

        if (newBranch.nodes.forall(_ == EmptyNode) && newBranch.value.isEmpty) EmptyNode else newBranch
      case leaf@Leaf(oldKey, _) =>
        if (!key.startsWith(oldKey.bytes)) leaf
        else {
          if (key sameElements oldKey.bytes) EmptyNode else leaf
        }
      case ext@Extension(oldKey, subNode) =>
        if (!key.startsWith(oldKey.bytes)) ext
        else remove(key.drop(oldKey.bytes.length), subNode) match {
              case subBranch@Branch(_,_) => ext.copy(node = subBranch)
              case subLeaf@Leaf(subKey, _) => subLeaf.copy(key = new NodeKey(oldKey.bytes ++ subKey.bytes,true) )
              case subExt@Extension(subKey, _) => subExt.copy(key = new NodeKey(oldKey.bytes ++ subKey.bytes,false))
              case node => node
            }
        }
    }


  private [trie] def put[V](key: Array[Byte], newValue: V, node: Node[V]): Node[V] = {
    node match {
      case EmptyNode =>
        Leaf(new NodeKey(key, true), newValue)
      case branch@Branch(nodes, _) =>
        if (key.isEmpty) branch.copy(value = Some(newValue))
        else branch.copy(nodes = nodes.updated(index = key.head, elem = put(key.tail, newValue, nodes(key.head))))
      case Leaf(oldKey, oldValue) =>
        val (matched, pathRemains, oldKeyRemains) = matching(key, oldKey.bytes)

        val subNode = {
          if (pathRemains.isEmpty && oldKeyRemains.isEmpty) {
            Leaf(oldKey, newValue)
          } else if (pathRemains.nonEmpty && oldKeyRemains.isEmpty) {
            val branch = Branch(value = Some(oldValue))
            branch.copy(nodes =
              branch.nodes.updated(index = pathRemains.head,
                                   elem = Leaf(new NodeKey(pathRemains.tail, true), newValue)))
          } else {
            var branch = Branch(value = if (pathRemains.isEmpty) Some(newValue) else None)
            branch = branch.copy(nodes =
              branch.nodes.updated(index = oldKeyRemains.head,
                                    elem = Leaf(new NodeKey(oldKeyRemains.tail, true), oldValue)))
            if (pathRemains.isEmpty) branch
            else branch.copy(nodes =
              branch.nodes.updated(index = pathRemains.head,
                                    elem = Leaf(new NodeKey(pathRemains.tail, true), newValue)))
          }
        }

        if (matched.nonEmpty) Extension(new NodeKey(matched, false), subNode) else subNode
      case Extension(oldKey, oldValue) =>
        val (matched, pathRemains, oldKeyRemains) = matching(key, oldKey.bytes)

        val subNode = {
          if (oldKeyRemains.isEmpty) {
            put(pathRemains, newValue, oldValue)
          } else {
            var branch = Branch(value = if (pathRemains.isEmpty) Some(newValue) else None)

            branch = branch.copy(nodes =
              branch.nodes.updated(index = oldKeyRemains.head,
                                    elem =  if (oldKeyRemains.tail.isEmpty) oldValue
                                            else Extension(new NodeKey(oldKeyRemains.tail, false), oldValue)))
            if (pathRemains.isEmpty) branch
            else branch.copy(nodes =
              branch.nodes.updated(index = pathRemains.head,
                                    elem = Leaf(new NodeKey(pathRemains.tail, true), newValue)))

          }
        }

        if (matched.nonEmpty) Extension(new NodeKey(matched, false), subNode) else subNode
    }
  }

  private [trie] val matching: (Array[Byte], Array[Byte]) => (Array[Byte], Array[Byte], Array[Byte]) = { (left, right) =>
    val matched = (left zip right).takeWhile { case (a, b) => a == b }.length
    (left.take(matched), left.drop(matched), right.drop(matched))
  }

}
