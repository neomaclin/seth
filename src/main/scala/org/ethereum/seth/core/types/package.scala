package org.ethereum.seth.core

import java.time.{LocalDateTime, ZoneOffset}

import cats.Eq
import org.ethereum.seth.core.hash.KECCAK_256
import org.ethereum.seth.core.rlp._

package object types {


  type B = Array[Byte]
  type P = BigInt

  type Address = Hash160

  type PoW = (P256, Hash256)

  type Hash256 = B32
  type Hash160 = B20
  type Hash64 = B8

  implicit def P2B(p: P): B = p.toByteArray

  implicit def B2P(b: B): P = BigInt(b)

  implicit def P256asB32(p: P256): B32 = p.p.toByteArray
  implicit def P5asB1(p: P5): B1 = BigInt(p.p).toByteArray

  implicit def timeToP256(time: LocalDateTime): P256 = BigInt(time.toEpochSecond(ZoneOffset.UTC))

  val B0 = Array.empty[Byte]

  implicit class B1(val b: B) extends AnyVal

  implicit class B8(val b: B) extends AnyVal

  implicit class B20(val b: B) extends AnyVal

  implicit class B32(val b: B) extends AnyVal

  implicit class B64(val b: B) extends AnyVal

  implicit class B256(val b: B) extends AnyVal

  implicit class U160(val p: BigInt) extends AnyVal

  implicit class P5(val p: Int) extends AnyVal

  implicit class P256(val p: BigInt) extends AnyVal {
    def +(m: P256): P256 = p.+(m.p)

    def -(m: P256): P256 = p.-(m.p)

    def /(m: P256): P256 = p./(m.p)

    def *(m: P256): P256 = p.*(m.p)

    def >=(m: P256): Boolean = p.>(m.p) || p.equals(m.p)

    def <=(m: P256): Boolean = p.<(m.p) || p.equals(m.p)

    def >(m: P256): Boolean = p.>(m.p)

    def <(m: P256): Boolean = p.<(m.p)
  }

  implicit object P256Eq extends Eq[P256] {
    override def eqv(x: P256, y: P256): Boolean = x.p.equals(y.p)
  }


  implicit object Hash160Eq extends Eq[Hash160] {
    override def eqv(x: Hash160, y: Hash160): Boolean = x.b sameElements y.b
  }

  implicit object Hash256Eq extends Eq[Hash256] {
    override def eqv(x: Hash256, y: Hash256): Boolean = x.b sameElements y.b
  }

  implicit object ByteArrayEq extends Eq[Array[Byte]] {
    override def eqv(x: Array[Byte], y: Array[Byte]): Boolean = x sameElements y
  }

  implicit object ByteArray20RLPEncoding extends RLPEncodable[B20] {
    def from(value: B20): RLPItem = value.b
  }

  implicit object ByteArray32RLPEncoding extends RLPEncodable[B32] {
    def from(value: B32): RLPItem = value.b
  }

  implicit object P256RLPEncoding extends RLPEncodable[P256] {
    def from(value: P256): RLPItem = value.p.toByteArray
  }

  implicit object ByteArray64RLPEncoding extends KECCAK_256[B64] {
    def sha3(value: B64): Hash256 = ???
  }



}
