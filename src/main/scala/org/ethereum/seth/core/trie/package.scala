package org.ethereum.seth.core

import org.ethereum.seth.core.types.B
import org.ethereum.seth.core.hash._

package object trie {

  type TRIE = Set[(B, B)]

  def c(tree: TRIE, i: Boolean): Array[Byte] = ???

  def n(tree: TRIE, i: Boolean) = {
    if (tree.isEmpty) Seq.empty[Byte]
    else {
      val encoded: Array[Byte] = c(tree,i)
      if (encoded.take(33).length <= 32) encoded else hash.sha3(encoded)
    }
  }

}
