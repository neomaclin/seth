package org.ethereum.seth.evm

import org.ethereum.seth.core.types.Hash256
import shapeless._

package object instructions {

  sealed trait Instruction

  //Stop and Arithmetic Operations
  case object STOP extends Instruction

  case object ADD extends Instruction

  case object MUL extends Instruction

  case object SUB extends Instruction

  case object DIV extends Instruction

  case object SDIV extends Instruction

  case object MOD extends Instruction

  case object SMOD extends Instruction

  case object ADDMOD extends Instruction

  case object MULMOD extends Instruction

  case object EXP extends Instruction

  case object SIGNEXTEND extends Instruction

  case object LT extends Instruction

  case object SLT extends Instruction

  case object GT extends Instruction

  case object SGT extends Instruction

  case object EQ extends Instruction

  case object ISZERO extends Instruction

  case object AND extends Instruction

  case object OR extends Instruction

  case object XOR extends Instruction

  case object NOT extends Instruction

  case object BYTE extends Instruction

  // SHA3
  case object SHA3 extends Instruction

  case object ADDRESS extends Instruction

  case object BALANCE extends Instruction

  case object ORIGIN extends Instruction

  case object CALLER extends Instruction

  case object CALLVALUE extends Instruction

  case object CALLDATALOAD extends Instruction

  case object CALLDATASIZE extends Instruction

  case object CALLDATACOPY extends Instruction

  case object CODESIZE extends Instruction

  case object CODECOPY extends Instruction

  case object GASPRICE extends Instruction

  case object EXTCODESIZE extends Instruction

  case object EXTCODECOPY extends Instruction

  case object BLOCKHASH extends Instruction

  case object COINBASE extends Instruction

  case object TIMESTAMP extends Instruction

  case object NUMBER extends Instruction

  case object DIFFICULTY extends Instruction

  case object GASLIMIT extends Instruction

  ////Stack, Memory, Storage and Flow Operations
  case object POP extends Instruction

  case object MLOAD extends Instruction

  case object MSTORE extends Instruction

  case object MSTORES extends Instruction

  case object SLOAD extends Instruction

  case object SSTORE extends Instruction

  case object JUMP extends Instruction

  case object JUMPI extends Instruction

  case object PC extends Instruction

  case object MSIZE extends Instruction

  case object GAS extends Instruction

  case object JUMPDEST extends Instruction

  //Push Operations
  case object PUSH1 extends Instruction

  case object PUSH2 extends Instruction

  case object PUSH3 extends Instruction

  case object PUSH4 extends Instruction

  case object PUSH5 extends Instruction

  case object PUSH6 extends Instruction

  case object PUSH7 extends Instruction

  case object PUSH8 extends Instruction

  case object PUSH9 extends Instruction

  case object PUSH10 extends Instruction

  case object PUSH11 extends Instruction

  case object PUSH12 extends Instruction

  case object PUSH13 extends Instruction

  case object PUSH14 extends Instruction

  case object PUSH15 extends Instruction

  case object PUSH16 extends Instruction

  case object PUSH17 extends Instruction

  case object PUSH18 extends Instruction

  case object PUSH19 extends Instruction

  case object PUSH20 extends Instruction

  case object PUSH21 extends Instruction

  case object PUSH22 extends Instruction

  case object PUSH23 extends Instruction

  case object PUSH24 extends Instruction

  case object PUSH25 extends Instruction

  case object PUSH26 extends Instruction

  case object PUSH27 extends Instruction

  case object PUSH28 extends Instruction

  case object PUSH29 extends Instruction

  case object PUSH30 extends Instruction

  case object PUSH31 extends Instruction

  case object PUSH32 extends Instruction

  case object DUP1 extends Instruction

  case object DUP2 extends Instruction

  case object DUP3 extends Instruction

  case object DUP4 extends Instruction

  case object DUP5 extends Instruction

  case object DUP6 extends Instruction

  case object DUP7 extends Instruction

  case object DUP8 extends Instruction

  case object DUP9 extends Instruction

  case object DUP10 extends Instruction

  case object DUP11 extends Instruction

  case object DUP12 extends Instruction

  case object DUP13 extends Instruction

  case object DUP14 extends Instruction

  case object DUP15 extends Instruction

  case object DUP16 extends Instruction

  case object SWAP1 extends Instruction

  case object SWAP2 extends Instruction

  case object SWAP3 extends Instruction

  case object SWAP4 extends Instruction

  case object SWAP5 extends Instruction

  case object SWAP6 extends Instruction

  case object SWAP7 extends Instruction

  case object SWAP8 extends Instruction

  case object SWAP9 extends Instruction

  case object SWAP10 extends Instruction

  case object SWAP11 extends Instruction

  case object SWAP12 extends Instruction

  case object SWAP13 extends Instruction

  case object SWAP14 extends Instruction

  case object SWAP15 extends Instruction

  case object SWAP16 extends Instruction

  //Logging Operations
  case object LOG0 extends Instruction

  case object LOG1 extends Instruction

  case object LOG2 extends Instruction

  case object LOG3 extends Instruction

  case object LOG4 extends Instruction

  //System operations

  case object CREATE extends Instruction

  case object CALL extends Instruction

  case object CALLCODE extends Instruction

  case object RETURN extends Instruction

  case object DELEGATECALL extends Instruction

  case object INVALID extends Instruction

  case object SELFDESTRUCT extends Instruction

  val opcodeMapping = Map[Int, Instruction](
    //Stop and Arithmetic Operations
    0x00 -> STOP,
    0x01 -> ADD,
    0x02 -> MUL,
    0x03 -> SUB,
    0x04 -> DIV,
    0x05 -> SDIV,
    0x06 -> MOD,
    0x07 -> SMOD,
    0x08 -> ADDMOD,
    0x09 -> MULMOD,
    0x0a -> EXP,
    0x0b -> SIGNEXTEND,
    //Comparison and Bitwise Logic Operations
    0x10 -> LT,
    0x11 -> GT,
    0x12 -> SLT,
    0x13 -> SGT,
    0x14 -> EQ,
    0x15 -> ISZERO,
    0x16 -> AND,
    0x17 -> OR,
    0x18 -> XOR,
    0x19 -> NOT,
    0x1a -> BYTE,
    //SHA3
    0x20 -> SHA3,
    // Environmental Information
    0x30 -> ADDRESS,
    0x31 -> BALANCE,
    0x32 -> ORIGIN,
    0x33 -> CALLER,
    0x34 -> CALLVALUE,
    0x35 -> CALLDATALOAD,
    0x36 -> CALLDATASIZE,
    0x37 -> CALLDATACOPY,
    0x38 -> CODESIZE,
    0x39 -> CODECOPY,
    0x3a -> GASPRICE,
    0x3b -> EXTCODESIZE,
    0x3c -> EXTCODECOPY,
    // Block Information
    0x40 -> BLOCKHASH,
    0x41 -> COINBASE,
    0x42 -> TIMESTAMP,
    0x43 -> NUMBER,
    0x44 -> DIFFICULTY,
    0x45 -> GASLIMIT,
    //Stack, Memory, Storage and Flow Operations
    0x50 -> POP,
    0x51 -> MLOAD,
    0x52 -> MSTORE,
    0x53 -> MSTORES,
    0x54 -> SLOAD,
    0x55 -> SSTORE,
    0x56 -> JUMP,
    0x57 -> JUMPI,
    0x58 -> PC,
    0x59 -> MSIZE,
    0x5a -> GAS,
    0x5b -> JUMPDEST,
    //Push Operations
    0x60 -> PUSH1,
    0x61 -> PUSH2,
    0x62 -> PUSH3,
    0x63 -> PUSH4,
    0x64 -> PUSH5,
    0x65 -> PUSH6,
    0x66 -> PUSH7,
    0x67 -> PUSH8,
    0x68 -> PUSH9,
    0x69 -> PUSH10,
    0x6a -> PUSH11,
    0x6b -> PUSH12,
    0x6c -> PUSH13,
    0x6d -> PUSH14,
    0x6e -> PUSH15,
    0x6f -> PUSH16,
    0x70 -> PUSH17,
    0x71 -> PUSH18,
    0x72 -> PUSH19,
    0x73 -> PUSH20,
    0x74 -> PUSH21,
    0x75 -> PUSH22,
    0x76 -> PUSH23,
    0x77 -> PUSH24,
    0x78 -> PUSH25,
    0x79 -> PUSH26,
    0x7a -> PUSH27,
    0x7b -> PUSH28,
    0x7c -> PUSH29,
    0x7d -> PUSH30,
    0x7e -> PUSH31,
    0x7f -> PUSH32,
    //Pup Operations
    0x80 -> DUP1,
    0x81 -> DUP2,
    0x82 -> DUP3,
    0x83 -> DUP4,
    0x84 -> DUP5,
    0x85 -> DUP6,
    0x86 -> DUP7,
    0x87 -> DUP8,
    0x88 -> DUP9,
    0x89 -> DUP10,
    0x8a -> DUP11,
    0x8b -> DUP12,
    0x8c -> DUP13,
    0x8d -> DUP14,
    0x8e -> DUP15,
    0x8f -> DUP16,
    //Swap Operations
    0x90 -> SWAP1,
    0x91 -> SWAP2,
    0x92 -> SWAP3,
    0x93 -> SWAP4,
    0x94 -> SWAP5,
    0x95 -> SWAP6,
    0x96 -> SWAP7,
    0x97 -> SWAP8,
    0x98 -> SWAP9,
    0x99 -> SWAP10,
    0x9a -> SWAP11,
    0x9b -> SWAP12,
    0x9c -> SWAP13,
    0x9d -> SWAP14,
    0x9e -> SWAP15,
    0x9f -> SWAP16,
    //Log Operations
    0xa0 -> LOG1,
    0xa1 -> LOG2,
    0xa2 -> LOG3,
    0xa3 -> LOG4
  )
}
