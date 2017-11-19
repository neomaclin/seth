package org.ethereum.seth.core

import org.ethereum.seth.core.types._
import org.ethereum.seth.core.hash._

import scala.math._

package object math {

  val Wei = BigInt(10).pow(0)
  val Szabo = BigInt(10).pow(12)
  val Finney = BigInt(10).pow(15)
  val Ether = BigInt(10).pow(18)
  val P256_MAX = BigInt(2).pow(256)


  val J_Wordbytes = 4 // Bytes in word.
  val J_datasetinit = BigInt(2).pow(30) // Bytes in dataset at genesis.
  val J_datasetgrowth = BigInt(2).pow(23) // Dataset growth per epoch.
  val J_cacheinit = BigInt(2).pow(24) // Bytes in cache at genesis.
  val J_cachegrowth = BigInt(2).pow(17) //Cache growth per epoch.
  val J_epoch = 30000 //Blocks per epoch.
  val J_mixbytes = 128 //mix length in bytes.
  val J_hashbytes = 64 //Hash length in bytes.
  val J_parents = 256 //Number of parents of each dataset element.
  val J_cacherounds = 3 // Number of rounds in cache production.
  val J_accesses = 64 //Number of accesses in hashimoto loop.

  def max(left: P256, right: P256): P256 =  if (left > right) left else right

 // def collapse[K,V](k:K, v:V) = (hash.sha3(k), rlp.encode(v))

  def ecdsaPubKey(key: B32): B64 = ???
  def ecdsaSign(e: B32, key: B32): (B1, B32, B32) = ???
  def ecdsaRecover(e: B32, v: B1, r: B32, s: B32): B64 = ???

}
