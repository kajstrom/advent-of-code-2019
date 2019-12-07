package day02

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day02 {
  def operation(opCode: Int, a: Int, b: Int): Int = {
    if (opCode == 1) {
      a + b
    } else {
      a * b
    }
  }

  def execute(intcodes: ListBuffer[Int]): ListBuffer[Int] = {
    for (opCodeIdx <- Range(0, intcodes.length, 4)) {
      val opCode = intcodes(opCodeIdx)

      println("Opcode idx", opCodeIdx)

      if (opCode == 99) {
        println("Terminator...")
        return intcodes
      }

      val a = intcodes(intcodes(opCodeIdx + 1))
      val b = intcodes(intcodes(opCodeIdx + 2))
      val toPosition = intcodes(opCodeIdx + 3)

      intcodes(toPosition) = operation(opCode, a, b)
    }

    intcodes
  }

  def main(args: Array[String]): Unit = {
    val opcodes = Source.fromResource("day02.txt").getLines.toList.head.split(",").map(opcode => opcode.toInt).toList
    val opcodesMutable = opcodes.to(ListBuffer)
    opcodesMutable(1) = 12
    opcodesMutable(2) = 2

    println("Opcodes total: ", opcodesMutable.length)

    println("Day 1, part 1: " + execute(opcodesMutable).head)
  }
}
