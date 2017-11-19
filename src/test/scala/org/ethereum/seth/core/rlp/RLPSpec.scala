package org.ethereum.seth.core.rlp


import org.scalatest.FlatSpec

import scala.languageFeature.implicitConversions

class RLPSpec extends FlatSpec {

  "A single byte whose value is in the [0x00, 0x7f] range " should " be its own RLP encoding" in {
    val single: Array[Byte] = Array(4)
    assert(encode(single) sameElements single)
  }

  "empty byte array " should "be [0x80] " in {
    assert(encode(Array.empty[Byte]).hexString == "[0x80]")
  }

  "empty sequence of RLP Structure " should "be [0xc0] " in {
    assert(encode(Seq.empty[RLPItem]).hexString == "[0xc0]")
  }

  "scalar values like 15 or 1024" should "be [0x0f] and [0x82,0x04,0x00]" in {
    assert(encode(15).hexString == "[0x0f]")
    assert(encode(1024).hexString == "[0x82,0x04,0x00]")
  }

  "String dog " should "be  [0x83,'d','o','g']" in {
    assert(encode("dog").hexString == s"[0x83,${"0x%02x".format('d'.toByte)},${"0x%02x".format('o'.toByte)},${"0x%02x".format('g'.toByte)}]")
  }

  "seq of strings such as [cat,dog] " should "be [0xc8,0x83,'c','a','t',0x83,'d','o','g']" in {
    val seq: Seq[RLPItem] = Seq("cat", "dog")
    assert(encode(seq).hexString == s"[0xc8,0x83,${"0x%02x".format('c'.toByte)},${"0x%02x".format('a'.toByte)},${"0x%02x".format('t'.toByte)}" +
      s",0x83,${"0x%02x".format('d'.toByte)},${"0x%02x".format('o'.toByte)},${"0x%02x".format('g'.toByte)}]")
  }

  " [[], [[]], [ [],[[]] ]" should "be [0xc7,0xc0,0xc1,0xc0,0xc3,0xc0,0xc1,0xc0]" in {
    val seq: Seq[RLPItem] =
      Seq(Seq.empty[RLPItem],
        Seq[RLPItem](
          Seq.empty[RLPItem]
        ),
        Seq[RLPItem](
          Seq.empty[RLPItem],
          Seq[RLPItem](Seq.empty[RLPItem]
          )
        )
      )
    assert(encode(seq).hexString == "[0xc7,0xc0,0xc1,0xc0,0xc3,0xc0,0xc1,0xc0]")
  }
//
//  "[0x80]" should "be decoded to empty byte array " in {
//
//    val (Some(ByteArrayRLPItem(array)),_) = decode(Array(0x80).map(_.toByte))
//    assert(array.isEmpty)
//  }

//  "[0xc0]" should "be a empty seq of RLPItems" in {
//
//    val (Some(RLPItemSeq(s)),_) = decode(Array(0xc0).map(_.toByte))
//    assert(s.isEmpty)
//  }
//
//  " [0x83,'d','o','g'] " should "be 'dog' " in {
//
//    val (Some(ByteArrayRLPItem(array)),_) = decode(Array(0x83,'d','o','g').map(_.toByte))
//    assert(array.map(_.toChar).mkString == "dog")
//  }
//
//  " [0xc8,0x83,'d','o','g',0x83,'c','a','t'] " should "be ['dog','cat'] " in {
//
//    val (Some(RLPItemSeq(seq)),_) = decode(Array(0xc8,0x83,'d','o','g',0x83,'c','a','t').map(_.toByte))
//
//    println(seq.map{
//      case ByteArrayRLPItem(seq) => seq.map(_.toChar).mkString
//      case _ => "Error"
//    }.mkString("[",",","]"))
//   }


}
