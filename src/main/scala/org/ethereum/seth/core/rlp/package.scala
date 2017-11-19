package org.ethereum.seth.core

import cats._

package object rlp {

  import scala.languageFeature.implicitConversions

  sealed trait DecodeError

  case object InvalidRLPLength extends DecodeError

  case object RLPFeedNotReady extends DecodeError


  trait RLPItem {
    def size: Int
  }

  final case class B(b: Array[Byte]) extends RLPItem {
    def size: Int = b.length
  }

  final case class L(l: Seq[RLPItem]) extends RLPItem {
    def size: Int = l.size
  }

  implicit def toRLPItem(str: String): RLPItem = StringRLPEncoding.from(str)

  implicit def toRLPItem(b: Array[Byte]): RLPItem = B(b)

  implicit def toRLPItem(s: Seq[RLPItem]): RLPItem = L(s)

  val RLPLzero = Seq.empty[RLPItem]

  val RLPBzero = Array.empty[Byte]

  def encode[T](l: T)(implicit e: RLPEncodable[T]): Array[Byte] = e.encode(l)

  def decode[T](l: Array[Byte])(implicit e: RLPDecodable[T]): T = e.decode(l)

  //  def decodeStream(r: Streaming[Byte]): (Either[DecodeError, RLPItem], Streaming[Byte]) = {
  //     r match {
  //       case Empty() =>
  //         (Left(InvalidRLPLength), Empty())
  //       case Cons(head, trailing) =>{
  //         head match {
  //           case x if x < BOffsetShort && x > 0  =>
  //           case x if =>
  //           case x if =>
  //           case x if =>
  //           case x if =>
  //         }
  //       }
  //       case Wait(waiting) => {
  //         (Left(RLPFeedNotReady), r)
  //       }
  //     }
  //  }
  //e.decode(r)
  //  }

  //  private def scan(r: Streaming[Byte], error: List[DecodeError] = Nil): (List[DecodeError], Streaming[Byte]) = {
  //    if (r.isEmpty) (error, r)
  //    else ()
  //  }

  //  def validate(r: Streaming[Byte]): Boolean = {
  //    val ( errors, trail ) = scan(r)
  //    errors.isEmpty && trail.isEmpty
  //  }

  //  def decode(input: Array[Byte]): (Option[RLPItem], Array[Byte]) = {
  //
  //    input.toList match {
  //      case Nil => (None, Array.empty[Byte])
  //      case head :: Nil if head < B_OFFSET_SHORT.toByte && head > 0 =>
  //        (Some(ByteArrayRLPItem(Array(head))), Array.empty[Byte])
  //      case head :: Nil if head == B_OFFSET_SHORT.toByte =>
  //        (Some[RLPItem](RLPBzero), Array.empty[Byte])
  //      case head :: seq if head > B_OFFSET_SHORT.toByte && head < B_OFFSET_LONG.toByte =>
  //        val expectedSize: Int = head - B_OFFSET_SHORT.toByte
  //        (Some[RLPItem](seq.take(expectedSize).toArray), seq.drop(expectedSize).toArray)
  //      case head :: Nil if head == L_OFFSET_SHORT.toByte =>
  //        (Some[RLPItem](RLPTzero), Array.empty[Byte])
  //      case head :: seq if head > L_OFFSET_SHORT.toByte && head < L_OFFSET_LONG.toByte =>
  //        var subItems = Seq.empty[RLPItem]
  //        var remains = seq.toArray
  //        var result:Option[RLPItem] = None
  //        do {
  //          val results = decode(remains)
  //          result = results._1
  //          remains = results._2
  //          subItems = subItems ++ result
  //        } while (remains.length > 0)
  //        (Some[RLPItem](subItems), Array.empty[Byte])
  //      case seq => ???
  //        //TODO: decode the byteArray generated for Int/Long/BigInt, and the Recursive RLPStructure
  //    }
  //  }

  //  /**
  //    * Allow for content up to size of 2 pow 64 bytes *
  //    **/
  //  private val MAX_ITEM_LENGTH = BigInt(256).pow(8)

  private val SizeThreshold = 56

  /** RLP encoding rules are defined as follows: */

  private val BOffsetShort = 0x80

  private val BOffsetLong = 0xb7

  private val LOffsetShort = 0xc0

  private val LOffsetLong = 0xf7

  private[rlp] object byteArray {
    val encode: Array[Byte] => Array[Byte] = {
      case Array(b) if b < BOffsetShort && b > 0 => Array(b)
      case array if array.length < SizeThreshold => (BOffsetShort + array.length).toByte +: array
      case array => (BOffsetLong + BE(array.length).length).toByte +: (BE(array.length) ++ array)
    }
  }

  private[rlp] object rlpItem {

    private def s(x: Seq[RLPItem]) = x.foldLeft(Array.empty[Byte])(_ ++ rlp.encode[RLPItem](_)(RLPItemRLPEncoding))

    val encode: Seq[RLPItem] => Array[Byte] = {
      case x if x.size < SizeThreshold => (LOffsetShort + s(x).length).toByte +: s(x)
      case x => (LOffsetLong + BE(s(x).length).length).toByte +: (BE(s(x).length) ++ s(x))
    }

  }


  trait RLPEncodable[T] {
    def from(value: T): RLPItem

    final def encode(value: T): Array[Byte] = from(value) match {
      case B(b) => rlp.byteArray.encode(b)
      case L(l) => rlp.rlpItem.encode(l)
    }
  }

  trait RLPDecodable[T] {
    def decode(bytes: Array[Byte]): T
  }

  implicit object BigIntRLPEncoding extends RLPEncodable[BigInt] {
    def from(value: BigInt): RLPItem = value.toByteArray
  }

  implicit object StringRLPEncoding extends RLPEncodable[String] {
    def from(value: String): RLPItem = value.toArray.map(_.toByte)
  }

  implicit object LongRLPEncoding extends RLPEncodable[Long] {
    def from(value: Long): RLPItem = BE(value)
  }

  implicit object IntRLPEncoding extends RLPEncodable[Int] {
    def from(value: Int): RLPItem = BE(value)
  }

  implicit object ByteRLPEncoding extends RLPEncodable[Byte] {
    def from(value: Byte): RLPItem = Array(value)
  }

  implicit object CharRLPEncoding extends RLPEncodable[Char] {
    def from(value: Char): RLPItem = Array(value.toByte)
  }

  implicit object ByteArrayRLPEncoding extends RLPEncodable[Array[Byte]] {
    def from(value: Array[Byte]): RLPItem = value
  }

  implicit object RLPItemRLPEncoding extends RLPEncodable[RLPItem] {
    def from(value: RLPItem): RLPItem = value
  }

  implicit object SeqRLPItemRLPEncoding extends RLPEncodable[Seq[RLPItem]] {
    def from(value: Seq[RLPItem]): RLPItem = value
  }

  private def BE(int: Int): Array[Byte] = BigInt(int).toByteArray

  private def BE(int: Long): Array[Byte] = BigInt(int).toByteArray

  implicit class ByteArrayOps(val b: Array[Byte]) extends AnyVal {
    def hexString: String = b.map("0x%02x".format(_)).mkString("[", ",", "]")
  }

  implicit object RLPItemEquality extends Eq[RLPItem] {
    override def eqv(x: RLPItem, y: RLPItem): Boolean = (x, y) match {
      case (B(xb), B(yb)) => xb sameElements yb
      case (L(xs), L(ys)) => xs sameElements ys
      case _ => false
    }
  }

  implicit object SeqRLPItemEquality extends Eq[Seq[RLPItem]] {
    override def eqv(x: Seq[RLPItem], y: Seq[RLPItem]): Boolean = x sameElements y
  }

}
