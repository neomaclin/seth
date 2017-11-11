package org.ethereum.seth.core

package object hash {

  val ETHASH_EPOCH_LENGTH = 30000
  val ETHASH_CACHE_ROUNDS = 3
  val ETHASH_MIX_BYTES = 128
  val ETHASH_ACCESSES = 64
  val ETHASH_DATASET_PARENTS = 256

  private val  DATASET_BYTES_INIT = 1 << 30
  private val  DATASET_BYTES_GROWTH = 1 << 23
  private val  CACHE_BYTES_INIT = 1 << 24
  private val  CACHE_BYTES_GROWTH = 1 << 17
  private val  NODE_WORDS = 64 / 4
  private val  NODE_BYTES = 64
  private val  MIX_WORDS = ETHASH_MIX_BYTES / 4
  private val  MIX_NODES = MIX_WORDS / NODE_WORDS
  private val  FNV_PRIME = 0x01000193

}
