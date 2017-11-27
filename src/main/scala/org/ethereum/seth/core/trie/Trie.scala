package org.ethereum.seth.core.trie

import org.ethereum.seth.core.trie

final class Trie[V] {
  private var root: Node[V] = EmptyNode


  def put(key: Array[Byte], value: V): Trie[V] = {
    root = trie.put(key, value, root)
    this
  }

  def putAll(entries: Seq[(Array[Byte], V)]): Trie[V] = {
    root = trie.putAll[V](root, entries)
    this
  }

}

object Trie {

  def apply[V]: Trie[V] = new Trie()

  def apply[V](key: Array[Byte], value: V): Trie[V] = new Trie().put(key,value)

  def apply[V](entries: (Array[Byte], V)*): Trie[V] = new Trie().putAll(entries)
}