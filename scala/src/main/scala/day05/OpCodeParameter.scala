package day05

import scala.collection.mutable.ListBuffer

case class OpCodeParameter(instruction: Int) {
  private val instructionString = instruction.toString.reverse.padTo(5, '0')
  private val parameterModes = instructionString.substring(2).toList.map(_.asDigit)

  val opCode: Int = instructionString.substring(0, 2).replace('0', ' ').trim.toInt

  def getParameterMode(nth: Int): Int = {
    if (nth >= parameterModes.length) {
      0
    } else {
      parameterModes(nth)
    }
  }

  def execute(intcodes: ListBuffer[Int], instructionPointer: Int, input: () => Int, output: Int => Unit): Unit = {
    opCode match {
      case 1 => add(instructionPointer, intcodes)
      case 2 => multiply(instructionPointer, intcodes)
      case 3 => readInput(instructionPointer, intcodes, input)
      case 4 => this.output(instructionPointer, intcodes, output)
    }
  }

  private def add(pointer: Int, intcodes: ListBuffer[Int]): Unit = {
    val a = getParam(0, pointer, intcodes)
    val b = getParam(1, pointer, intcodes)

    intcodes(intcodes(3 + pointer)) = a + b
  }

  private def multiply(pointer: Int, intcodes: ListBuffer[Int]): Unit = {
    val a = getParam(0, pointer, intcodes)
    val b = getParam(1, pointer, intcodes)

    intcodes(intcodes(3 + pointer)) = a * b
  }

  private def readInput(pointer: Int, intcodes: ListBuffer[Int], input: () => Int): Unit = {
    intcodes(intcodes(1 + pointer)) = input()
  }

  private def output(pointer: Int, intcodes: ListBuffer[Int], output: Int => Unit): Unit = {
    output(getParam(0, pointer, intcodes))
  }

  private def getParam(nth: Int, pointer: Int, intcodes: ListBuffer[Int]) = {
    val paramPointer = nth + 1 + pointer // Handle off-by-one from using 0 as first param
    var value = 999999

    if (getParameterMode(nth) == 0) {
      // Position mode
      value = intcodes(intcodes(paramPointer))
    } else {
      // Immediate mode
      value = intcodes(paramPointer)
    }

    value
  }

  def stepsForward: Int = {
    opCode match {
      case 1 => 4
      case 2 => 4
      case 3 => 2
      case 4 => 2
    }
  }
}
