package org.ethereum.seth

import org.ethereum.seth.core.transaction._
import org.ethereum.seth.core.transaction.validation._
import org.ethereum.seth.core.gas._
import org.ethereum.seth.core.types.P256

package object core {

//  def transitionOf(state: State, tx: Transaction): State = {
//
//
//    val intrinsicGasCost: BigInt = {
//      val payloadCost = if (tx.payload.isEmpty) G_txdatazero else G_txdatanonzero
//      val creationCost = if (tx.fields.to.b.isEmpty) G_txcreate else 0
//
//      payloadCost + creationCost + G_transaction
//    }
//
//    val upfrontCost: P256 = tx.fields.gasLimit * tx.fields.gasPrice + tx.fields.value
//    require(intrinsicGasCost <= tx.fields.gasLimit)
//
//
//    val gasRemains = tx.fields.gasLimit - intrinsicGasCost
//
//
//  }
}
