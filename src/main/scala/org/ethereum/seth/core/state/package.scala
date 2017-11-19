package org.ethereum.seth.core

import dogs._
import org.ethereum.seth.core.block._
import org.ethereum.seth.core.rlp._
import org.ethereum.seth.core.types._

package object state {

  //type WorldState = (Address, Array[Byte])

  val SubState_Zero = SubState(Set.empty[Account], List.empty[Log], BigInt(0))

  final case class SubState(accounts: Set[Account],
                            logs: List[Log],
                            refunds: P256)

  final case class State(account: Account,
                         remainingGas: P256,
                         subState: SubState)

  //val EmptySeqHash = hash.sha3(Seq.empty)


  final case class Account(nonce: P256,
                           balance: P256,
                           storageRoot: B32,
                           codeHash: B32)


  implicit object AccountEncodable extends RLPEncodable[Account] {
    def from(account: Account): RLPItem = Seq[RLPItem](
      account.nonce.p.toByteArray,
      account.balance.p.toByteArray,
      account.storageRoot.b,
      account.codeHash.b
    )
  }
  //  def v0(tx: TX): Int = tx.signed.gasLimit * tx.signed.gasPrice + tx.signed.value

//  def itemHashing[K,V](item: (Array[Byte], P)):(Hash256, Array[Byte]) = (hash.sha3(item._1), rlp.encode(item._2))
//  def trieItemHashing(address: Address, account: Account):State = (hash.sha3(address), rlp.encode(account))
// // def rootHash(tree: TRIE): Hash256 = tree.map((k,v) => (hash.sha3(k), rlp.encode(y))).fold(Array.empty[Byte])

}
