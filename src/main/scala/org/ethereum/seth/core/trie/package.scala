package org.ethereum.seth.core

import cats._
import cats.implicits._
import org.ethereum.seth.core.math.HexPrefix

package object trie {


  sealed trait Node[+V]

  final class NodeKey(val bytes: Array[Byte], sign: Boolean) {
    val compacted = HexPrefix.encode(bytes, sign)
    override def toString = compacted.hexString
  }

  case object EmptyNode extends Node[Nothing]

  final case class Leaf[+V](key: NodeKey, value: V) extends Node[V]

  final case class Extension[+V](key: NodeKey, node: Node[V]) extends Node[V]

  final case class Branch[+V](nodes: Vector[Node[V]] = Vector.fill[Node[V]](16) {EmptyNode},
                              value: Option[V]) extends Node[V]

  private [trie] def putAll[V](onNode: Node[V], entries: Seq[(Array[Byte], V)]): Node[V] = {
    def putKV[V](node: Node[V], entry: (Array[Byte], V)): Node[V] = put(entry._1, entry._2, node)

    Foldable[List].foldLeft(entries.toList, onNode)(putKV[V](_, _))
  }


  //  def get[V](key: NodeKey, fromNode: Node[V]): Option[V] ={}

  //  def remove[V](key: Array[Byte], fromNode: Node[V]): Node[V] = {
  //    fromNode match {
  //      case EmptyNode => EmptyNode
  //      case branch@Branch(nodes, _) =>
  //        if (key.isEmpty) {}
  //        branch.copy(value = None)
  //    else {
  //    val pos = key.bytes.head
  //    branch.copy (nodes = nodes.updated (pos, remove (NodeKey (key.bytes.tail), nodes (pos) ) ) )
  //    }
  //      case leaf@Leaf() =>
  //      case ext@Ext() =>
  //    }
  //  }

  private [trie] def put[V](key: Array[Byte], newValue: V, node: Node[V]): Node[V] = {

    node match {
      case EmptyNode =>
        Leaf(new NodeKey(key, true), newValue)
      case branch@Branch(nodes, _) =>
        if (key.isEmpty) {
          branch.copy(value = Some(newValue))
        } else {
          branch.copy(nodes = nodes.updated(index = key.head,
                                            elem = put(key.tail, newValue, nodes(key.head))))
        }
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
