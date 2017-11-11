package org.ethereum.seth.evm

import org.ethereum.seth.core.types.Hash256
import shapeless._

package object instructions {

  sealed trait Instruction[A]

  //Stop and Arithmetic Operations
  case object STOP extends Instruction[Nothing]

  final case class ADD[A](left: A, right: A) extends Instruction[A]

  final case class MUL[A](left: A, right: A) extends Instruction[A]

  final case class SUB[A](left: A, right: A) extends Instruction[A]

  final case class DIV[A](left: A, right: A) extends Instruction[A]

  final case class SDIV[A](left: A, right: A) extends Instruction[A]

  final case class MOD[A](left: A, right: A) extends Instruction[A]

  final case class SMOD[A](left: A, right: A) extends Instruction[A]

  final case class ADDMOD[A](left: A, right: A, mod: Int) extends Instruction[A]

  final case class MULMOD[A](left: A, right: A, mod: Int) extends Instruction[A]

  final case class EXP[A](v: A, x: A) extends Instruction[A]

  final case class SIGNEXTEND[A](c1: A, c2: A) extends Instruction[A]

  final case class LT[A](left: A, right: A) extends Instruction[A]

  final case class SLT[A](left: A, right: A) extends Instruction[A]

  final case class GT[A](left: A, right: A) extends Instruction[A]

  final case class SGT[A](left: A, right: A) extends Instruction[A]

  final case class EQ[A](left: A, right: A) extends Instruction[A]

  final case class ISZERO[A](value: A) extends Instruction[A]

  final case class AND[A](left: A, right: A) extends Instruction[A]

  final case class OR[A](left: A, right: A) extends Instruction[A]

  final case class XOR[A](left: A, right: A) extends Instruction[A]

  final case class NOT[A](value: A) extends Instruction[A]

  final case class BYTE[A](zero: A, one: A) extends Instruction[A]

  // SHA3
  final case class SHA3[A](value: A) extends Instruction[A]

  case object ADDRESS extends Instruction[Hash256]

  final case class BALANCE[A](account: A) extends Instruction[A]

  case object ORIGIN extends Instruction[Hash256]

  case object CALLER extends Instruction[Hash256]

  case object CALLVALUE extends Instruction[Hash256]

  final case class CALLDATALOAD[A](value: A) extends Instruction[A]

  case object CALLDATASIZE extends Instruction[Long]

  final case class CALLDATACOPY[A](value1: A, original: A, value2: A) extends Instruction[Unit]

  case object CODESIZE extends Instruction[Long]

  final case class CODECOPY[A](value1: A, original: A, value2: A) extends Instruction[Unit]

  case object GASPRICE extends Instruction[Long]

  final case class EXTCODESIZE[A](value1: A) extends Instruction[Long]

  final case class EXTCODECOPY[A](value1: A, original: A, value2: A, ext: A) extends Instruction[Unit]

  final case class BLOCKHASH[A](value: A) extends Instruction[A]

  case object COINBASE extends Instruction[Long]

  case object TIMESTAMP extends Instruction[Long]

  case object NUMBER extends Instruction[Long]

  case object DIFFICULTY extends Instruction[Long]

  case object GASLIMIT extends Instruction[Long]

  ////Stack, Memory, Storage and Flow Operations
  final case class POP[A](value: A) extends Instruction[Unit]

  final case class MLOAD[A](value: A) extends Instruction[Long]

  final case class MSTORE[A](value: A) extends Instruction[Long]

  final case class MSTORES[A](value: A) extends Instruction[Long]

  final case class SLOAD[A](value: A) extends Instruction[Long]

  final case class SSTORE[A](value: A, value2: A) extends Instruction[Unit]

  final case class JUMP[A](value: A) extends Instruction[Unit]

  final case class JUMPI[A](value: A, value2: A) extends Instruction[Unit]

  case object PC extends Instruction[Long]

  case object MSIZE extends Instruction[Long]

  case object GAS extends Instruction[Long]

  case object JUMPDEST extends Instruction[Unit]

  //Push Operations
  case object PUSH1 extends Instruction[Long]

  case object PUSH2 extends Instruction[Long]

  case object PUSH3 extends Instruction[Long]

  case object PUSH4 extends Instruction[Long]

  case object PUSH5 extends Instruction[Long]

  case object PUSH6 extends Instruction[Long]

  case object PUSH7 extends Instruction[Long]

  case object PUSH8 extends Instruction[Long]

  case object PUSH9 extends Instruction[Long]

  case object PUSH10 extends Instruction[Long]

  case object PUSH11 extends Instruction[Long]

  case object PUSH12 extends Instruction[Long]

  case object PUSH13 extends Instruction[Long]

  case object PUSH14 extends Instruction[Long]

  case object PUSH15 extends Instruction[Long]

  case object PUSH16 extends Instruction[Long]

  case object PUSH17 extends Instruction[Long]

  case object PUSH18 extends Instruction[Long]

  case object PUSH19 extends Instruction[Long]

  case object PUSH20 extends Instruction[Long]

  case object PUSH21 extends Instruction[Long]

  case object PUSH22 extends Instruction[Long]

  case object PUSH23 extends Instruction[Long]

  case object PUSH24 extends Instruction[Long]

  case object PUSH25 extends Instruction[Long]

  case object PUSH26 extends Instruction[Long]

  case object PUSH27 extends Instruction[Long]

  case object PUSH28 extends Instruction[Long]

  case object PUSH29 extends Instruction[Long]

  case object PUSH30 extends Instruction[Long]

  case object PUSH31 extends Instruction[Long]

  case object PUSH32 extends Instruction[Long]

  final case class DUP1[A](value: A :: HNil) extends Instruction[A :: A :: HNil]

  final case class DUP2[A](value: A :: A :: HNil) extends Instruction[A :: A :: A :: HNil]

  final case class DUP3[A](value: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: HNil]

  final case class DUP4[A](value: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: HNil]

  final case class DUP5[A](value: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP6[A](value: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP7[A](value: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP8[A](value: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP9[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP10[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP11[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP12[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP13[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP14[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP15[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class DUP16[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP1[A](value: A :: A :: HNil) extends Instruction[A :: A :: HNil]

  final case class SWAP2[A](value: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: HNil]

  final case class SWAP3[A](value: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: HNil]

  final case class SWAP4[A](value: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: HNil]

  final case class SWAP5[A](value: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP6[A](value: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP7[A](value: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP8[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP9[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP10[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP11[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP12[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP13[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP14[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP15[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  final case class SWAP16[A](value: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: A :: HNil]

  //Logging Operations
  final case class LOG0[A](value: A :: A :: HNil) extends Instruction[Unit]

  final case class LOG1[A](value: A :: A :: A :: HNil) extends Instruction[Unit]

  final case class LOG2[A](value: A :: A :: A :: A :: HNil) extends Instruction[Unit]

  final case class LOG3[A](value: A :: A :: A :: A :: A :: HNil) extends Instruction[Unit]

  final case class LOG4[A](value: A :: A :: A :: A :: A :: A :: HNil) extends Instruction[Unit]

  //System operations

  final case class CREATE[A](value: A :: A :: HNil) extends Instruction[Unit]

  final case class CALL[A](value: A :: A :: HNil) extends Instruction[Unit]

  final case class CALLCODE[A](value: A :: A :: HNil) extends Instruction[Unit]

  final case class RETURN[A](value: A :: A :: HNil) extends Instruction[Unit]

  final case class DELEGATECALL[A](value: A :: A :: HNil) extends Instruction[Unit]

  final case class SUICIDE[A](account: A) extends Instruction[Unit]

}
