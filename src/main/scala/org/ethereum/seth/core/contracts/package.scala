package org.ethereum.seth.core

import dogs._
import org.ethereum.seth.core.state._
import org.ethereum.seth.core.types._


package object contracts {
  //  final case class Contract(state: Set[State],
  //                            gas: P256,
  //                            subState: SubState,
  //                            logs: Array[Log])

  //For contract contract creation
  //(σ′,g′,A) ≡ Λ(σ,s,o,g,p,v,i,e)
  // There are a number of intrinsic parameters used
  // when creating an account: sender (s),
  // original transactor (o),
  // available gas (g),
  // gas price (p),
  // endowment (v)
  // together with an arbitrary length byte array, i, the initialisation EVM code
  // and finally the present depth of the message- call/contract-creation stack (e).
  def contractCreation(account: Account,
                       sender: Address,
                       originalTransactor: Address,
                       gasAvailable: P256,
                       gasPrice: P256,
                       endowment: P256,
                       init: Streaming[Byte],
                       stackDepth: Int): SubState = ???

  //For message call
  //(σ ,g ,A,o) ≡ Θ(σ,s,o,r,c,g,p,v,v ̃,d,e)
  // parameters are required: sender (s),
  // transaction originator (o),
  // recipient (r),
  // the account whose code is to be executed (c, usually the same as recipient),
  // available gas (g),
  // value (v)
  // and gas price (p) together with an arbitrary length byte array, d, the input data of the call
  // and finally the present depth of the message-call/contract-creation stack (e).

  def messageCall(account: Account,
                  sender: Address,
                  originalTransactor: Address,
                  recipient: Address,
                  executedAccount: Address,
                  gasAvailable: P256,
                  gasPrice: P256,
                  value: P256,
                  data: Streaming[Byte],
                  stackDepth: Int): SubState = ???
}
