package org.ethereum.seth.core

import cats.Eq

package object rlp {

  import scala.languageFeature.implicitConversions

  val RLPTzero = Seq.empty[RLPItem]

  val RLPBzero = Array.empty[Byte]

  def encode[T](l: T)(implicit e: RLPEncodeable[T]): Array[Byte] = e.encode(l)

  def decode(input: Array[Byte]): (Option[RLPItem], Array[Byte]) = {

    input.toList match {
      case Nil => (None, Array.empty[Byte])
      case head :: Nil if head < B_OFFSET_SHORT.toByte && head > 0 =>
        (Some(ByteArrayRLPItem(Array(head))), Array.empty[Byte])
      case head :: Nil if head == B_OFFSET_SHORT.toByte =>
        (Some[RLPItem](RLPBzero), Array.empty[Byte])
      case head :: seq if head > B_OFFSET_SHORT.toByte && head < B_OFFSET_LONG.toByte =>
        val expectedSize: Int = head - B_OFFSET_SHORT.toByte
        (Some[RLPItem](seq.take(expectedSize).toArray), seq.drop(expectedSize).toArray)
      case head :: Nil if head == L_OFFSET_SHORT.toByte =>
        (Some[RLPItem](RLPTzero), Array.empty[Byte])
      case head :: seq if head > L_OFFSET_SHORT.toByte && head < L_OFFSET_LONG.toByte =>
        var subItems = Seq.empty[RLPItem]
        var remains = seq.toArray
        var result:Option[RLPItem] = None
        do {
          val results = decode(remains)
          result = results._1
          remains = results._2
          subItems = subItems ++ result
        } while (remains.length > 0)
        (Some[RLPItem](subItems), Array.empty[Byte])
      case seq => ???
        //TODO: decode the byteArray generated for Int/Long/BigInt, and the Recursive RLPStructure
    }
  }

  /**
    * Allow for content up to size of 2^64 bytes *
    **/
  private val MAX_ITEM_LENGTH = BigInt(256).pow(8)

  /**
    * Reason for threshold according to Vitalik Buterin:
    * - 56 bytes maximizes the benefit of both options
    * - if we went with 60 then we would have only had 4 slots for long strings
    * so RLP would not have been able to store objects above 4gb
    * - if we went with 48 then RLP would be fine for 2^128 space, but that's way too much
    * - so 56 and 2^64 space seems like the right place to put the cutoff
    * - also, that's where Bitcoin's variant does the cutoff
    */
  private val SIZE_THRESHOLD = 56

  /** RLP encoding rules are defined as follows: */

  /*
   * For a single byte whose value is in the [0x00, 0x7f] range, that byte is
   * its own RLP encoding.
   * Refer to Yellow Paper . Appendix B for details
   */

  private val B_OFFSET_SHORT = 0x80

  private val B_OFFSET_LONG = 0xb7

  private val L_OFFSET_SHORT = 0xc0

  private val L_OFFSET_LONG = 0xf7

  private[rlp] object byteArray {
    def encode(b: Array[Byte]): Array[Byte] = b match {
      case Array(b) if b < B_OFFSET_SHORT && b > 0 => Array(b)
      case array if array.length < SIZE_THRESHOLD => (B_OFFSET_SHORT + array.length).toByte +: array
      case array => (B_OFFSET_LONG + BE(array.length).length).toByte +: (BE(array.length) ++ array)
    }
  }

  private[rlp] object rlpItem {
    def encode(s: Seq[RLPItem]): Array[Byte] = {
      def recursively(s: Seq[RLPItem]) = s.flatMap(x => rlp.encode(x)(rlp.RLPItemRLPEncoding)).toArray[Byte]

      s match {
        case seq if seq.size < SIZE_THRESHOLD =>
          (L_OFFSET_SHORT + recursively(seq).length).toByte +: recursively(seq)
        case seq =>
          (L_OFFSET_LONG + BE(recursively(seq).length).length).toByte +: (BE(recursively(seq).length) ++ recursively(seq))
      }
    }

  }

  trait RLPEncodeable[T] {
    def encode(value: T): Array[Byte]
  }

  trait RLPItem {
    def size: Int
  }

  final case class ByteArrayRLPItem(b: Array[Byte]) extends RLPItem {
    def size: Int = b.length
  }

  final case class RLPItemSeq(l: Seq[RLPItem]) extends RLPItem {
    def size: Int = l.size
  }

  implicit object BigIntRLPEncoding extends RLPEncodeable[BigInt] {
    def encode(value: BigInt): Array[Byte] = rlp.byteArray.encode(value.toByteArray)
  }

  implicit object StringRLPEncoding extends RLPEncodeable[String] {
    def encode(value: String): Array[Byte] = rlp.byteArray.encode(value.toArray.map(_.toByte))
  }

  implicit object LongRLPEncoding extends RLPEncodeable[Long] {
    def encode(value: Long): Array[Byte] = rlp.byteArray.encode(BE(value))
  }

  implicit object IntRLPEncoding extends RLPEncodeable[Int] {
    def encode(value: Int): Array[Byte] = rlp.byteArray.encode(BE(value))
  }

  implicit object ByteRLPEncoding extends RLPEncodeable[Byte] {
    def encode(value: Byte): Array[Byte] = rlp.byteArray.encode(Array(value))
  }

  implicit object CharRLPEncoding extends RLPEncodeable[Char] {
    def encode(value: Char): Array[Byte] = rlp.byteArray.encode(Array(value.toByte))
  }

  implicit object ByteArrayRLPEncoding extends RLPEncodeable[Array[Byte]] {
    def encode(value: Array[Byte]): Array[Byte] = rlp.byteArray.encode(value)
  }

  implicit object RLPItemRLPEncoding extends RLPEncodeable[RLPItem] {
    def encode(value: RLPItem): Array[Byte] = value match {
      case ByteArrayRLPItem(b) => rlp.byteArray.encode(b)
      case RLPItemSeq(l) => rlp.rlpItem.encode(l)
    }
  }

  implicit def toRLPItem(s: String): RLPItem = ByteArrayRLPItem(s.map(_.toByte).toArray)

  implicit def toRLPItem(b: Array[Byte]): RLPItem = ByteArrayRLPItem(b)

  implicit def toRLPItem(s: Seq[RLPItem]): RLPItem = RLPItemSeq(s)

  implicit object SeqRLPItemRLPEncoding extends RLPEncodeable[Seq[RLPItem]] {
    def encode(value: Seq[RLPItem]): Array[Byte] = rlp.rlpItem.encode(value)
  }

  private def BE(int: Int): Array[Byte] = BigInt(int).toByteArray

  private def BE(int: Long): Array[Byte] = BigInt(int).toByteArray

  implicit class ByteArrayOps(val b: Array[Byte]) extends AnyVal {
    def hexString: String = b.map("0x%02x".format(_)).mkString("[", ",", "]")
  }

  implicit object RLPItemEquality extends Eq[RLPItem] {
    override def eqv(x: RLPItem, y: RLPItem): Boolean = (x, y) match {
      case (ByteArrayRLPItem(xb), ByteArrayRLPItem(yb)) => xb sameElements yb
      case (RLPItemSeq(xs), RLPItemSeq(ys)) => xs sameElements ys
      case _ => false
    }
  }

  implicit object SeqRLPItemEquality extends Eq[Seq[RLPItem]] {
    override def eqv(x: Seq[RLPItem], y: Seq[RLPItem]): Boolean = x sameElements y
  }

}
