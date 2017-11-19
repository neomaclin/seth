package org.ethereum.seth.core.block

import java.time.ZoneOffset

import cats.implicits._
import org.ethereum.seth.core.math.max
import org.ethereum.seth.core.types._


package object validation {

  val D0 = BigInt(131072)
  val N0 = BigInt(0)
  val N1 = BigInt(1)
  val N10 = BigInt(10)
  val GasLimitMin = BigInt(125000)
  val N2048 =  BigInt(2048)
  val N1024 =  BigInt(1024)
  val N99  = BigInt(-99)
  val N100000 = BigInt(100000)


  object header {

    def parentOf(h: Header): Block = ???

    def hasValidBlockNumber(header: Header): Boolean = {
      (parentOf(header).header.number + BigInt(1)) === header.number
    }

    def hasValidDifficulty(header: Header): Boolean = {
      val parentHeader = parentOf(header).header

      def x: P256 = parentHeader.difficulty / N2048

      def s: P256 = max(N1 - (header.timestamp - parentHeader.timestamp) / N10, N99)

      def e: P256 = BigInt(2).pow((header.number / N100000).p.toInt - 2)

      header.number.p match {
        case N0 => header.difficulty === D0
        case _ => header.difficulty === max(D0, parentHeader.difficulty + (x * s) + e)
      }
    }

    def hasValidGasLimit(header: Header): Boolean = {
      val parentHeader = parentOf(header).header

      def notExceedMax: Boolean = header.gasLimit < (parentHeader.gasLimit + parentHeader.gasLimit) / N1024

      def notExceedMin: Boolean = header.gasLimit > (parentHeader.gasLimit - parentHeader.gasLimit) / N1024

      def atLeast125000: Boolean = header.gasLimit >= GasLimitMin

      notExceedMax && notExceedMin && atLeast125000
    }

    def hasValidTimeStamp(header: Header): Boolean = {
      header.timestamp.isAfter(parentOf(header).header.timestamp)
    }

    def hasValidNonce(header: Header, pow: PoW): Boolean = {
      val (n, m) = pow
      n <= (BigInt(2).pow(256) / header.difficulty) && m === header.mixHash
    }

    def hasValidExtraData(header: Header): Boolean = {
      header.extraData.b.length <= 32
    }

    def hasValidGasConsumption(header: Header): Boolean = {
      header.gasUsed < header.gasLimit
    }

    def isValid(header: Header, givenPoW: PoW): Boolean = {
      hasValidNonce(header, givenPoW) &&
        hasValidDifficulty(header) &&
        hasValidGasLimit(header) &&
        hasValidGasConsumption(header) &&
        hasValidTimeStamp(header) &&
        hasValidBlockNumber(header) &&
        hasValidExtraData(header)
    }
  }

  object block {
    def hasValidstateRoot: Boolean = ???
    def hasValidUncleHash: Boolean = ???
    def hasValidtxRoot: Boolean = ???
    def hasValidReceiptsRoot: Boolean = ???
    def hasValidLog: Boolean = ???


  }

}
