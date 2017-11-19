package org.ethereum.seth.evm

import dogs.Streaming
import org.ethereum.seth.core.block._
import org.ethereum.seth.core.gas
import org.ethereum.seth.core.state._
import org.ethereum.seth.core.types._


package object executions {

  type MemoryContent = List[BigInt]
  type GlobalState = Streaming[Account]

  final case class Environment(accountAddress: U160,              //Ia
                               senderAddress: U160,               //Io
                               gasPrice: P256,                    //Ip
                               executionInput: Streaming[Byte],   //Id
                               transactionSenderAddress: U160,    //Is
                               value: BigInt,                     //Iv
                               executionCode: Map[P256,Streaming[Byte]],    //Ib
                               header: Header,                    //Ih
                               depth: BigInt)                     //Ic

  final case class MachineState(gasAvailable: P256,               //g
                                programCounter: P256,             //pc
                                memoryContent: MemoryContent,     //m
                                activeWordCount: P256,            //i
                                stackContent: List[Byte])          //s


  def exceptionalHalt(globalState: GlobalState,
                      machineState: MachineState,
                      environment: Environment): Boolean = {
    def currentOperation = {
      if (machineState.programCounter < BigInt(environment.executionCode.size) ) {
        environment.executionCode(machineState.programCounter)
      } else "STOP"
    }
    def hasSufficientGas = machineState.gasAvailable < gas.costOf(globalState, machineState, environment)
    def hasInvalidInstruction = ???
    def hasSufficientStack = ??? //machineState.stackContent.size <
    def hasValidOperation = ???
    def hasValidStackSize = ???

    hasSufficientGas || hasInvalidInstruction || hasSufficientStack || hasValidOperation || hasValidStackSize

  }
}
