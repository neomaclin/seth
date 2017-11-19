package org.ethereum.seth.core

import java.time.LocalDateTime

import bloomfilter.mutable.BloomFilter
import org.ethereum.seth.core.rlp.{RLPEncodable, RLPItem}
import org.ethereum.seth.core.state.Account
import org.ethereum.seth.core.transaction._
import org.ethereum.seth.core.types._

package object block {
//  val GensisBlock = Block(
//    Header(
//
//    ),
//    List.empty[Transaction],
//    List.empty[Header]
//  )

  final case class Header(parentHash: Hash256,
                          unclesHash: Hash256,
                          beneficiary: U160,
                          stateRoot: Hash256,
                          txRoot: Hash256,
                          receiptRoot: Hash256,
                          logsBloom: Hash256,
                          difficulty: P256,
                          number: P256,
                          timestamp: LocalDateTime,
                          gasLimit: P256,
                          gasUsed: P256,
                          extraData: B32,
                          mixHash: Hash256,
                          nonce: Hash64)

  final case class Block(header: Header,
                         transactions: List[SignedTransaction],
                         uncles: List[Header])

  final case class Log(address: B20,
                       topics: List[B32],
                       data: Array[Byte])

  final case class Receipt(postTxState: Hash256,
                           gasUsed: P256,
                           logs: List[Log],
                           logsBloom: BloomFilter[Log])


  implicit object ReceiptEncodable extends RLPEncodable[Receipt] {
    def from(receipt: Receipt): RLPItem = ???
  }

}
