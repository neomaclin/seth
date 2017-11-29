package org.ethereum.seth.core.trie

import org.ethereum.seth.core.trie

final class Trie[V] {
  private var _root: Node[V] = EmptyNode

  def root: Node[V] = _root

  def entries: Seq[(Array[Byte], V)] = trie.dump(root)

  def get(key: Array[Byte]): Option[V] = trie.get(key, root)

  def remove(key: Array[Byte]): Trie[V] = {
    _root = trie.remove(key, _root)
    this
  }

  def removeAll(keys: List[Array[Byte]]): Trie[V] = {
    _root = trie.removeAll(keys, _root)
    this
  }

  def put(key: Array[Byte], value: V): Trie[V] = {
    _root = trie.put(key, value, _root)
    this
  }

  def putAll(entries: Seq[(Array[Byte], V)]): Trie[V] = {
    _root = trie.putAll[V](_root, entries)
    this
  }

}

object Trie {

  def apply[V]: Trie[V] = new Trie()

  def apply[V](key: Array[Byte], value: V): Trie[V] = new Trie().put(key, value)

  def apply[V](entries: (Array[Byte], V)*): Trie[V] = new Trie().putAll(entries)

}