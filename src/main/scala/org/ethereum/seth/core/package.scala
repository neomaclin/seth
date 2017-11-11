package org.ethereum.seth

import java.time.LocalDateTime

import org.ethereum.seth.core.types._

package object core {

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
                         transactions: List[TX],
                         uncles: List[Header])


  final case class Account(nonce: P256,
                           balance: P256,
                           storageRoot: B32,
                           codeHash: B32)


  final case class Log(address: B20,
                       topics: List[B32],
                       data: Array[Byte])

  final case class Receipt(postTxState: Hash256,
                           gasUsed: P256,
                           logs: List[Log],
                           logsBloom: Hash256)

  sealed trait TX

  final case class SignedTransaction(nonce: P256,
                                     gasPrice: P256,
                                     gasLimit: P256,
                                     recipientAddress: Option[B20],
                                     value: P256,
                                     v: P5,
                                     r: P256,
                                     s: P256)

  final case class ContractCreationTx(tx: SignedTransaction, init: Array[Byte]) extends  TX

  final case class MessageCallTx(tx: SignedTransaction, data: Array[Byte]) extends  TX
}
