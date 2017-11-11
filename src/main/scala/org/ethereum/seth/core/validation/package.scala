package org.ethereum.seth.core

import types._
import math._


package object validation {

  val D0 = BigInt(131072)
  val N0 = BigInt(0)
  val GAS_LIMIT_MIN = BigInt(125000)

  object header{

    def parentOf(h: Header): Block = ???

    def isValidBlockNumber(header: Header): Boolean = {
      parentOf(header).header.number.+(BigInt(1)).p.equals(header.number.p)
    }

    def isValidDifficulty(header: Header): Boolean = {
      def x:BigInt = ???
      def s:BigInt = ???
      def e:BigInt = ???
      header.number.p match {
        case N0 => header.difficulty.p == D0
        case _ => header.difficulty.p == max(D0, parentOf(header).header.difficulty.p + (x * s) + e )
      }
    }

    def isValidGasLimit(header: Header): Boolean = {
      def notExceedMax: Boolean = ???
      def notExceedMin: Boolean = ???
      def atLeast125000: Boolean = header.gasLimit.p >= GAS_LIMIT_MIN

      notExceedMax && notExceedMin && atLeast125000
    }

    def isValidTimeStamp(header: Header): Boolean = {
      header.timestamp.isAfter(parentOf(header).header.timestamp)
    }

    def isValidNonce(header: Header, pow: PoW): Boolean = {
      val (n,m) = pow
      n.p.<=(BigInt(2).pow(256) / header.difficulty.p) && m.equals(header.mixHash)
    }

    def isValidExtraData(header: Header): Boolean = {
      header.extraData.b.length <= 32
    }

    def isValidGasConsumption(header: Header): Boolean = {
      header.gasUsed.p < header.gasLimit.p
    }

    def isValid(header: Header, givenPoW: PoW): Boolean = {
      isValidNonce(header, givenPoW) &&
        isValidDifficulty(header) &&
        isValidGasLimit(header) &&
        isValidGasConsumption(header) &&
        isValidTimeStamp(header) &&
        isValidBlockNumber(header) &&
        isValidExtraData(header)
    }
  }
}
