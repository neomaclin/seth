package org.ethereum.seth.evm

import dogs.Streaming
import org.ethereum.seth.core.block._
import org.ethereum.seth.core.gas
import org.ethereum.seth.core.state._
import org.ethereum.seth.core.types._

import scala.annotation.tailrec


package object executions {

  type MemoryContent = List[B32]
  type GlobalState = Streaming[Account]
  val opcodes = instructions.opcodeMapping.toList.foldLeft(Map.empty[String,Byte]){case (m, (k,v)) => m + (v -> k.toByte)}
  val Jumps = Set("JUMP","JUMPI").map(opcodes)
  val Pushs = Set("PUSH1", "PUSH2", "PUSH3", "PUSH4", "PUSH5", "PUSH6", "PUSH7", "PUSH8", "PUSH9", "PUSH10",
    "PUSH11", "PUSH12", "PUSH13", "PUSH14", "PUSH15", "PUSH16", "PUSH17", "PUSH18", "PUSH19", "PUSH20",
    "PUSH21", "PUSH22", "PUSH23", "PUSH24", "PUSH25", "PUSH26", "PUSH27", "PUSH28", "PUSH29", "PUSH30",
    "PUSH31", "PUSH32").map(opcodes)
  //val
  final case class Environment(accountAddress: U160,              //Ia
                               senderAddress: U160,               //Io
                               gasPrice: P256,                    //Ip
                               executionInput: Streaming[Byte],   //Id
                               transactionSenderAddress: U160,    //Is
                               value: BigInt,                     //Iv
                               executionCode: Map[P256, Byte],  //Ib
                               header: Header,                    //Ih
                               depth: BigInt)                     //Ic

  final case class MachineState(gasAvailable: P256,               //g
                                programCounter: P256,             //pc
                                memoryContent: MemoryContent,     //m
                                activeWordCount: P256,            //i
                                stackContent: List[Byte])          //s

//  def normalHalt( machineState: MachineState,
//                  environment: Environment): Boolean = {
//    def w =  currentOperation(machineState,environment)
//
//  }

  def currentOperation(machineState: MachineState,
                       environment: Environment): Byte = {
    if (machineState.programCounter < BigInt(environment.executionCode.size) ) {
      environment.executionCode(machineState.programCounter)
    } else opcodes("STOP")
  }

//  def exceptionalHalt(globalState: GlobalState,
//                      machineState: MachineState,
//                      environment: Environment): Boolean = {
//    def w =  currentOperation(machineState,environment)
//    def hasSufficientGas = machineState.gasAvailable < gas.costOf(globalState, machineState, environment)
//    def hasInvalidInstruction = ???
//    def hasSufficientStack = ??? //machineState.stackContent.size <
//    def hasValidOperation = Jumps.contains(w) && validJumpDest(environment.executionCode).contains(machineState.stackContent.head)
//    def hasValidStackSize = ???
//
//    hasSufficientGas || hasInvalidInstruction || hasSufficientStack || hasValidOperation || hasValidStackSize
//
//  }

//  @tailrec
//  def validJumpDest(codes: Map[P256, Byte], pc: BigInt = 0): Set[Byte] = {
//    if (pc >= codes.size) Set.empty[Byte]
//    else {
//      if (opcodes("JUMPDEST") == codes(pc)) Set(pc.toByte) ++ validJumpDest(codes, )
//      else validJumpDest()
//    }
//  }

  def nextInstructionPos(pc: BigInt, opcode: Byte): BigInt = ???
}
