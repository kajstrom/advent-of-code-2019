package day02

import utils.Utils

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

      if (opCode == 99) {
        return intcodes
      }

      val a = intcodes(intcodes(opCodeIdx + 1))
      val b = intcodes(intcodes(opCodeIdx + 2))
      val toPosition = intcodes(opCodeIdx + 3)

      intcodes(toPosition) = operation(opCode, a, b)
    }

    intcodes
  }

  def part1(opcodes: List[Int]): Int = {
    val opcodesMutable = opcodes.to(ListBuffer)
    opcodesMutable(1) = 12
    opcodesMutable(2) = 2

    execute(opcodesMutable).head
  }

  def part2(opcodes: List[Int]): Int = {
    for (noun <- Range(0, 99)) {
      for (verb <- Range(0, 99)) {
        val opcodesMutable = opcodes.to(ListBuffer)
        opcodesMutable(1) = noun
        opcodesMutable(2) = verb

        if (execute(opcodesMutable).head == 19690720) {
          return 100 * noun + verb
        }
      }
    }

    0
  }

  def main(args: Array[String]): Unit = {
    val opcodes = Source.fromResource("day02.txt").getLines.toList.head.split(",").map(opcode => opcode.toInt).toList

    Utils.time {
      println("Day 1, part 1: " + part1(opcodes))
    }

    Utils.time {
      println("Day 2, part 2: " + part2(opcodes))
    }
  }
}
