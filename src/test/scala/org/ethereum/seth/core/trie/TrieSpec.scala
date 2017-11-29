package org.ethereum.seth.core.trie

import org.scalatest.FlatSpec

class TrieSpec extends FlatSpec {

  val testList = List(
    (Array(6, 4, 6, 15, 6, 7).map(_.toByte), "puppy"),
    (Array(6, 4, 6, 15, 6, 7, 6, 5).map(_.toByte), "coin"),
    (Array(6, 4, 6, 15).map(_.toByte), "verb"),
    (Array(6, 8, 6, 15, 7, 2, 7, 3, 6, 5).map(_.toByte), "stallion"),
  )

  val testList2 = List(
    (Array(10, 7, 7, 13, 3, 3, 7).map(_.toByte), 1.00),
    (Array(10, 7, 15, 9, 3, 6, 5).map(_.toByte), 1.1),
    (Array(10, 7, 1, 1, 3, 5, 5).map(_.toByte), 45.0),
    (Array(10, 7, 7, 13, 3, 9, 7).map(_.toByte), 0.12)
  )

  "A leaf node " should " return if k-v is added to a Empty Node" in {
    val single: Node[String] = put(Array(6, 4, 6, 15).map(_.toByte), "verb", EmptyNode)
    assert(single.isInstanceOf[Leaf[String]])

  }
  "A trie " should " be generic contain of any value type " in {
    var stringTrie = Trie[String]()
    assert(stringTrie.putAll(testList).root.isInstanceOf[Extension[String]])

    val doubleTrie = Trie[Double]()
    assert(doubleTrie.putAll(testList2).root.isInstanceOf[Extension[Double]])
  }

//  "A trie " should " be generic contain of any value type " in {
//    var stringTrie = Trie[String]()
//    assert(stringTrie.putAll(testList).root.isInstanceOf[Extension[String]])
//
//    val doubleTrie = Trie[Double]()
//    assert(doubleTrie.putAll(testList2).root.isInstanceOf[Extension[Double]])
//  }
}
