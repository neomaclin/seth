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

  val opcodeMapping = Map[Int, String](
    //Stop and Arithmetic Operations
    0x00 -> "STOP",
    0x01 -> "ADD",
    0x02 -> "MUL",
    0x03 -> "SUB",
    0x04 -> "DIV",
    0x05 -> "SDIV",
    0x06 -> "MOD",
    0x07 -> "SMOD",
    0x08 -> "ADDMOD",
    0x09 -> "MULMOD",
    0x0a -> "EXP",
    0x0b -> "SIGNEXTEND",
    //Comparison and Bitwise Logic Operations
    0x10 -> "LT",
    0x11 -> "GT",
    0x12 -> "SLT",
    0x13 -> "SGT",
    0x14 -> "EQ",
    0x15 -> "ISZERO",
    0x16 -> "AND",
    0x17 -> "OR",
    0x18 -> "XOR",
    0x19 -> "NOT",
    0x1a -> "BYTE",
    //SHA3
    0x20 -> "SHA3",
    // Environmental Information
    0x30 -> "ADDRESS",
    0x31 -> "BALANCE",
    0x32 -> "ORIGIN",
    0x33 -> "CALLER",
    0x34 -> "CALLVALUE",
    0x35 -> "CALLDATALOAD",
    0x36 -> "CALLDATASIZE",
    0x37 -> "CALLDATACOPY",
    0x38 -> "CODESIZE",
    0x39 -> "CODECOPY",
    0x3a -> "GASPRICE",
    0x3b -> "EXTCODESIZE",
    0x3c -> "EXTCODECOPY",
    // Block Information
    0x40 -> "BLOCKHASH",
    0x41 -> "COINBASE",
    0x42 -> "TIMESTAMP",
    0x43 -> "NUMBER",
    0x44 -> "DIFFICULTY",
    0x45 -> "GASLIMIT",
    //Stack, Memory, Storage and Flow Operations
    0x50 -> "POP",
    0x51 -> "MLOAD",
    0x52 -> "MSTORE",
    0x53 -> "MSTORE8",
    0x54 -> "SLOAD",
    0x55 -> "SSTORE",
    0x56 -> "JUMP",
    0x57 -> "JUMPI",
    0x58 -> "PC",
    0x59 -> "MSIZE",
    0x5a -> "GAS",
    0x5b -> "JUMPDEST",
    //Push Operations
    0x60 -> "PUSH1",
    0x61 -> "PUSH2",
    0x62 -> "PUSH3",
    0x63 -> "PUSH4",
    0x64 -> "PUSH5",
    0x65 -> "PUSH6",
    0x66 -> "PUSH7",
    0x67 -> "PUSH8",
    0x68 -> "PUSH9",
    0x69 -> "PUSH10",
    0x6a -> "PUSH11",
    0x6b -> "PUSH12",
    0x6c -> "PUSH13",
    0x6d -> "PUSH14",
    0x6e -> "PUSH15",
    0x6f -> "PUSH16",
    0x70 -> "PUSH17",
    0x71 -> "PUSH18",
    0x72 -> "PUSH19",
    0x73 -> "PUSH20",
    0x74 -> "PUSH21",
    0x75 -> "PUSH22",
    0x76 -> "PUSH23",
    0x77 -> "PUSH24",
    0x78 -> "PUSH25",
    0x79 -> "PUSH26",
    0x7a -> "PUSH27",
    0x7b -> "PUSH28",
    0x7c -> "PUSH29",
    0x7d -> "PUSH30",
    0x7e -> "PUSH31",
    0x7f -> "PUSH32",
    //Pup Operations
    0x80 -> "DUP1",
    0x81 -> "DUP2",
    0x82 -> "DUP3",
    0x83 -> "DUP4",
    0x84 -> "DUP5",
    0x85 -> "DUP6",
    0x86 -> "DUP7",
    0x87 -> "DUP8",
    0x88 -> "DUP9",
    0x89 -> "DUP10",
    0x8a -> "DUP11",
    0x8b -> "DUP12",
    0x8c -> "DUP13",
    0x8d -> "DUP14",
    0x8e -> "DUP15",
    0x8f -> "DUP16",
    //Swap Operations
    0x90 -> "SWAP1",
    0x91 -> "SWAP2",
    0x92 -> "SWAP3",
    0x93 -> "SWAP4",
    0x94 -> "SWAP5",
    0x95 -> "SWAP6",
    0x96 -> "SWAP7",
    0x97 -> "SWAP8",
    0x98 -> "SWAP9",
    0x99 -> "SWAP10",
    0x9a -> "SWAP11",
    0x9b -> "SWAP12",
    0x9c -> "SWAP13",
    0x9d -> "SWAP14",
    0x9e -> "SWAP15",
    0x9f -> "SWAP16",
    //Log Operations
    0xa0 -> "LOG1",
    0xa1 -> "LOG2",
    0xa2 -> "LOG3",
    0xa3 -> "LOG4"
  )
}
