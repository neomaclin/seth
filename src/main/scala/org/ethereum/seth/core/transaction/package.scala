package org.ethereum.seth.core



import dogs._
import org.ethereum.seth.core.types._
import org.ethereum.seth.core.hash._
import cats.implicits._

package object transaction {

  val secp256k1n = BigInt("115792089237316195423570985008687907852837564279074904382605163141518161494337")

  private val V_ValidRange = Range[Int](27,28)
  private val R_ValidRange = Range[BigInt](0, secp256k1n)
  private val S_ValidRange = Range[BigInt](0, secp256k1n / 2 + 1 )

  sealed trait SigningError

  case object InvalidSignatures extends SigningError

  sealed trait Transaction {

    def fields: Common

    def signatures: Signatures

    def payload: Streaming[Byte]
  }

  final case class Common(nonce: P256,
                          gasPrice: P256,
                          gasLimit: P256,
                          to: B20,
                          value: P256)

  final case class Signatures(w: P5, r: P256, s: P256) {
    def isValid: Boolean = {
      V_ValidRange.contains(w.p) &&
        R_ValidRange.contains(r.p) &&
        S_ValidRange.contains(s.p)
    }

  }

  final case class Message(fields: Common, payload: Streaming[Byte])

  implicit object MessageKECCAK_256 extends KECCAK_256[Message] {
    def sha3(value: Message): Hash256 = ???
  }

  final case class ContractCreationTx(fields: Common,
                                      signatures: Signatures,
                                      init: Streaming[Byte]) extends Transaction {
    def payload = init
  }

  final case class MessageCallTx(fields: Common,
                                 signatures: Signatures,
                                 data: Streaming[Byte]) extends Transaction {
    def payload = data
  }

  final case class SignedTransaction(tx: Transaction, privateKey: B32) extends Transaction{
    def fields: Common  = tx.fields

    def signatures: Signatures = tx.signatures

    def payload: Streaming[Byte] = tx.payload
  }


  def address(privateKey: B32): B20 = sha3(ecdsaPubKey(privateKey)).b.drop(12)

  def senderOf(tx: Transaction): Address = {
    val Signatures(w,r,s) = tx.signatures
    sha3(ecdsaRecover(messageHash(tx),w,r,s)).b.drop(12)
  }



  def signing(transaction: Transaction, privateKey: B32): SignedTransaction = {
    val (w,r,s) = ecdsaSign(messageHash(transaction), privateKey)
    SignedTransaction(transaction, privateKey)
  }

  def messageHash(tx: Transaction): Hash256 = {
    val l = tx match {
      case ContractCreationTx(fields, _, i) => Message(fields, i)
      case MessageCallTx(fields, _, d) => Message(fields, d)
      case SignedTransaction(tx, privateKey) =>  Message(tx.fields, tx.payload)
    }

    sha3(l)
  }

  def assert(transaction: SignedTransaction): Boolean = senderOf(transaction) === address(transaction.privateKey)


}
