package org.ethereum.seth.core

package object gas {
  val G_zreo = 0 //Nothing paid for operations
  val G_base = 2 //Amount of gas to pay for operations of the set Wbase.
  val G_verylow = 3 //Amount of gas to pay for operations of the set Wverylow.
  val G_low = 5 //Amount of gas to pay for operations of the set Wlow.
  val G_mid = 8 //Amount of gas to pay for operations of the set Wmid.
  val G_high = 10 //Amount of gas to pay for operations of the set Whigh.
  val G_extcode = 700 //Amount of gas to pay for operations of the set Wextcode.
  val G_balance = 400 //Amount of gas to pay for a BALANCE operation.
  val G_sload = 200 //Paid for a SLOAD operation.
  val G_jumpdest = 1 //Paid for a JUMPDEST operation.
  val G_sset = 20000 //Paid for an SSTORE operation when the storage value is set to non-zero from zero.
  val G_sreset = 5000 //Paid for an SSTORE operation when the storage value’s zeroness remains unchanged or is set to zero.
  val R_sclear = 15000 //Refund given (added into refund counter) when the storage value is set to zero from non-zero.
  val R_suicide = 24000 //Refund given (added into refund counter) for suiciding an account.
  val G_suicide = 5000 //Amount of gas to pay for a SUICIDE operation.
  val G_create = 32000 //Paid for a CREATE operation.
  val G_codedeposit = 200 //Paid per byte for a CREATE operation to succeed in placing code into state.
  val G_call = 700 //Paid for a CALL operation.
  val G_callvalue = 9000 //Paid for a non-zero value transfer as part of the CALL operation.
  val G_callstipend = 2300 //A stipend for the called contract subtracted from G_callvalue for a non-zero value transfer.
  val G_newaccount = 25000 //Paid for a CALL or SUICIDE operation which creates an account.
  val G_exp = 10 // Partial payment for an EXP operation.
  val G_expbyte = 10 //Partial payment when multiplied by dlog256(exponent)e for the EXP operation.
  val G_memory = 3 //Paid for every additional word when expanding memory.
  val G_txcreate = 32000 //Paid by all contract-creating transactions after the Homestead transition.
  val G_txdatazero = 4 // Paid for every zero byte of data or code for a transaction.
  val G_txdatanonzero = 68 //Paid for every non-zero byte of data or code for a transaction.
  val G_transaction = 21000 //Paid for every transaction.
  val G_log = 375 //Partial payment for a LOG_ operation.
  val G_logdata = 8 //Paid for each byte in a LOG_ operation’s data.
  val G_logtopic = 375 //Paid for each topic of a LOG_ operation.
  val G_sha3 = 30 //Paid for each SHA3 operation.
  val G_sha3word = 6 //Paid for each word (rounded up) for input data to a SHA3 operation.
  val G_copy = 3 //Partial payment for *COPY operations, multiplied by words copied, rounded up.
  val G_blockhash = 20 //Payment for BLOCKHASH operation.
}
