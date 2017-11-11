package org.ethereum.seth.core

package object types {
  sealed trait Hash

  type Hash256 = Hash
  type Hash64 = Hash
  type Hash160 = Hash

 
  class B1(val b: Array[Byte]) extends AnyVal

  class B20(val b: Array[Byte]) extends AnyVal

  class B32(val b: Array[Byte]) extends AnyVal

  class B64(val b: Array[Byte]) extends AnyVal

  class B256(val b: Array[Byte]) extends AnyVal



  implicit class U160(val p: BigInt) extends AnyVal
  implicit class P5(val p: Int) extends AnyVal

  implicit class P256(val p: BigInt) extends AnyVal {
    def +(m:BigInt): P256 = p.+(m)
    def /(m:BigInt): P256 = p./(m)
    def *(m:BigInt): P256 = p.*(m)

  }

  type AddressType = U160

  type PoW = (P256, Hash256)

  trait Serializable[T] {
    def serialize(x: T): Array[Byte]

  }

  trait Deserializable[T] {
    def deserializable(byts: Array[Byte]): T
  }


}
