package day05

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day05 {
  def execute(intcodes: ListBuffer[Int], input: () => Int, output: Int => Unit): ListBuffer[Int] = {
    var instructionPointer = 0

    while (instructionPointer < intcodes.length) {
      val opCodeParam = OpCodeParameter(intcodes(instructionPointer))

      if (opCodeParam.opCode == 99) {
        return intcodes
      }

      opCodeParam.execute(intcodes, instructionPointer, input, output)

      instructionPointer += opCodeParam.stepsForward
    }

    println(s"Instruction pointer overflow: $instructionPointer")

    intcodes
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

  def main(args: Array[String]): Unit = {
    val opcodes: List[Int] = Source.fromResource("day05.txt").getLines.toList.head.split(",").map(opcode => opcode.toInt).toList

    part1(opcodes);
  }
}
