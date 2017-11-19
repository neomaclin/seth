package org.ethereum.seth.core.transaction

import cats.implicits._
import dogs._
import org.ethereum.seth.core.state._
import org.ethereum.seth.core.types.P256

package object validation {




  //  def validate(tx:Transaction, privateKey: B32): Boolean = {
  //
  //    val publicKey = ecdsaPubKey(privateKey)
  //    val (w, r, s) = ecdsaSign(messageHash(tx), privateKey)
  //    val isValidSigns = R_ValidRange.contains(r) && S_ValidRange.contains(s) && V_ValidRange.contains(w)
  //    def sender = hash.kec(ecdsaRecover(messageHash(tx), w, r, s)).drop(96)
  //    isValidSigns && (sender === address(privateKey))
  //
  //  }
  //



}
