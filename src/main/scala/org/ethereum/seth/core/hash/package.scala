package org.ethereum.seth.core

import org.ethereum.seth.core.types._

package object hash {

  val ETHASH_EPOCH_LENGTH = 30000
  val ETHASH_CACHE_ROUNDS = 3
  val ETHASH_MIX_BYTES = 128
  val ETHASH_ACCESSES = 64
  val ETHASH_DATASET_PARENTS = 256
  // val default

  private val DATASET_BYTES_INIT = 1 << 30
  private val DATASET_BYTES_GROWTH = 1 << 23
  private val CACHE_BYTES_INIT = 1 << 24
  private val CACHE_BYTES_GROWTH = 1 << 17
  private val NODE_WORDS = 64 / 4
  private val NODE_BYTES = 64
  private val MIX_WORDS = ETHASH_MIX_BYTES / 4
  private val MIX_NODES = MIX_WORDS / NODE_WORDS
  private val FNV_PRIME = 0x01000193

  def sha3[T](value: T)(implicit hashing: KECCAK_256[T]): Hash256 = hashing.sha3(value)

  trait KECCAK_256[T] {
    def sha3(value: T): Hash256
  }

  implicit object ByteArrayKECCAK_256 extends KECCAK_256[Array[Byte]] {
    def sha3(value: Array[Byte]): Hash256 = ???
  }


  def ripemd160[T](value: T): B20 = ???

  def ecdsaPubKey(key: B32): B64 = ???

  def ecdsaSign(e: B32, key: B32): (B1, B32, B32) = ???

  def ecdsaRecover(e: B32, v: B1, r: B32, s: B32): B64 = ???

}
