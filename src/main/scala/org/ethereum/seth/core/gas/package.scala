package org.ethereum.seth.core

import org.ethereum.seth.evm.executions._

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

  val W_zero = Set("STOP","RETURN")

  val W_base = Set(
    "ADDRESS",
    "ORIGIN",
    "CALLER",
    "CALLVALUE",
    "CALLDATASIZE",
    "CODESIZE",
    "GASPRICE",
    "COINBASE",
    "TIMESTAMP",
    "NUMBER",
    "DIFFICULTY",
    "GASLIMIT",
    "POP",
    "PC",
    "MSIZE",
    "GAS")

  val W_verylow = Set(
    "ADD", "SUB", "NOT", "LT", "GT", "SLT",
    "EQ",
    "ISZERO",
    "AND",
    "OR",
    "XOR",
    "BYTE",
    "CALLDATALOAD",
    "MLOAD",
    "MSTORE", "MSTORE8",
    "PUSH1", "PUSH2", "PUSH3", "PUSH4", "PUSH5", "PUSH6", "PUSH7", "PUSH8", "PUSH9", "PUSH10",
    "PUSH11", "PUSH12", "PUSH13", "PUSH14", "PUSH15", "PUSH16", "PUSH17", "PUSH18", "PUSH19", "PUSH20",
    "PUSH21", "PUSH22", "PUSH23", "PUSH24", "PUSH25", "PUSH26", "PUSH27", "PUSH28", "PUSH29", "PUSH30",
    "PUSH31", "PUSH32",
    "DUP1", "DUP2", "DUP3", "DUP4", "DUP5", "DUP6", "DUP7", "DUP8",
    "DUP9", "DUP10", "DUP11", "DUP12", "DUP13", "DUP14", "DUP15", "DUP16",
    "SWAP1", "SWAP2", "SWAP3", "SWAP4", "SWAP5", "SWAP6", "SWAP7", "SWAP8",
    "SWAP9", "SWAP10", "SWAP11", "SWAP12", "SWAP13", "SWAP14", "SWAP15", "SWAP16"
  )

  val W_low = Set("MUL","DIV","SDIV","MOD","SMOD","SIGNEXTEND")

  val W_mid = Set("ADDMOD","MULMOD","JUMP")

  val W_high = Set("JUMPI")

  val W_extcode = Set("EXTCODESIZE")

  def costOf(account: GlobalState, state: MachineState, environment: Environment): BigInt  = ???
//    def memoryCost = G_memory * a + a * a / 512
//    val w: String = if (environment.b.size > state.programCounter) environment.b(state.programCounter) else "STOP"
//    def cost = w match {
//      case "SSTORE" => ssstoreCost()
//      case "EXP" && state.stackContents == 0 => G_exp
//      case "EXP" && state.stackContents > 0 => G_exp + G_expbyte * (1 + log256)
//      case "CALLDATACOPY" | "CODECOPY"  => G_verylow + G_copy * ( /32)
//      case "EXTCODECOPY" => G_extcode + G_copy * (/32)
//      case "LOG0" => G_log + G_logdata *
//      case "LOG1" => G_log + G_logdata * G_logtopic
//      case "LOG2" => G_log + G_logdata * + 2 * G_logtopic
//      case "LOG3" => G_log + G_logdata * + 3 * G_logtopic
//      case "LOG4" => G_log + G_logdata * + 4 * G_logtopic
//      case "CALL" | "CALLCODE" | "DELEGATECALL" => callCost()
//      case "SELFDESTRUCT" => selfDesturctCost()
//      case "CREATE" => G_create
//      case "SHA3" => G_sha3 + G_sha3word *
//      case "JUMPDEST" => G_jumpdest
//      case "SLOAD" => G_sload
//      case s if W_zero.contains(s) => G_zreo
//      case s if W_base.contains(s) => G_base
//      case s if W_verylow.contains(s) => G_verylow
//      case s if W_low.contains(s) => G_low
//      case s if W_mid.contains(s) => G_mid
//      case s if W_high.contains(s) => G_high
//      case s if W_extcode.contains(s)  => G_extcode
//      case "BALANCE" => G_balance
//      case "BLOCKHASH" => G_blockhash
//    }
//
//    memoryCost(prevState) - memoryCost(newState) + cost
//  }

}
