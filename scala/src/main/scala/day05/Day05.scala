package day05

import day05.Operation.TerminalOperation

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day05 {
  def execute(intcodes: ListBuffer[Int], input: () => Int, output: Int => Unit): Unit = {
    var instructionPointer = 0

    while (instructionPointer < intcodes.length) {
      val opCodeOperation = Operation(instructionPointer, intcodes, input, output)

      if (opCodeOperation.isInstanceOf[TerminalOperation]) {
        return
      }

      opCodeOperation.execute
      instructionPointer = opCodeOperation.pointerLocation
    }

    println(s"Instruction pointer overflow: $instructionPointer")
  }

  def part1(opcodes: List[Int]): Unit = {

    execute(opcodes.to(ListBuffer),
      () => 1,
      toOutput => {
        if (toOutput > 0) {
          println(s"Part 1: $toOutput")
        }
      })
  }

  def part2(opcodes: List[Int]): Unit = {
    execute(opcodes.to(ListBuffer),
      () => 5,
      toOutput => {
        println(s"Part 2: $toOutput")
      })
  }

  def main(args: Array[String]): Unit = {
    var opcodes: List[Int] = Source.fromResource("day05.txt").getLines.toList.head.split(",").map(opcode => opcode.toInt).toList

    part1(opcodes)

    opcodes = Source.fromResource("day05.txt").getLines.toList.head.split(",").map(opcode => opcode.toInt).toList
    part2(opcodes)
  }
}
