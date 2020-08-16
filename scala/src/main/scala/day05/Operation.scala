package day05

import day05.Operation.{Input, Output}

import scala.collection.mutable.ListBuffer

abstract class Operation(pointer: Int, parameterModes: List[Int], intcodes: ListBuffer[Int]) {

  def pointerLocation: Int
  def execute: Unit

  protected def getParam(nth: Int) = {
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

  protected def getParameterMode(nth: Int): Int = {
    if (nth >= parameterModes.length) {
      0
    } else {
      parameterModes(nth)
    }
  }
}



object Operation {
  type Input = () => Int
  type Output = Int => Unit

  private class AddOperation(pointer: Int, intcodes: ListBuffer[Int], parameterModes: List[Int])
    extends Operation(pointer, parameterModes, intcodes) {
    override def pointerLocation: Int = pointer + 4

    override def execute: Unit = {
      val a = getParam(0)
      val b = getParam(1)

      intcodes(intcodes(3 + pointer)) = a + b
    }
  }

  private class MultiplyOperation(pointer: Int, intcodes: ListBuffer[Int], parameterModes: List[Int])
    extends Operation(pointer, parameterModes, intcodes) {
    override def pointerLocation: Int = pointer + 4

    override def execute: Unit = {
      val a = getParam(0)
      val b = getParam(1)

      intcodes(intcodes(3 + pointer)) = a * b
    }
  }

  private class InputOperation(pointer: Int, intcodes: ListBuffer[Int], parameterModes: List[Int], input: Input)
    extends Operation(pointer, parameterModes, intcodes) {
    override def pointerLocation: Int = pointer + 2

    override def execute: Unit = {
      intcodes(intcodes(1 + pointer)) = input()
    }
  }

  private class OutputOperation(pointer: Int, intcodes: ListBuffer[Int], parameterModes: List[Int], output: Output)
    extends Operation(pointer, parameterModes, intcodes) {
    override def pointerLocation: Int = pointer + 2

    override def execute: Unit = {
      output(getParam(0))
    }
  }

  private class TerminalOperation(pointer: Int, intcodes: ListBuffer[Int], parameterModes: List[Int])
    extends Operation(pointer, parameterModes, intcodes) {
    override def pointerLocation: Int = pointer + 2

    override def execute: Unit = {
      System.exit(0);
    }
  }

  def apply(pointer: Int, intcodes: ListBuffer[Int], input: Input, output: Output): Operation = {
    val instructionString = intcodes(pointer).toString.reverse.padTo(5, '0')
    val parameterModes = instructionString.substring(2).toList.map(_.asDigit)
    val opCode: Int = instructionString.substring(0, 2).replace('0', ' ').trim.toInt

    opCode match {
      case 1 => new AddOperation(pointer, intcodes, parameterModes)
      case 2 => new MultiplyOperation(pointer, intcodes, parameterModes)
      case 3 => new InputOperation(pointer, intcodes, parameterModes, input)
      case 4 => new OutputOperation(pointer, intcodes, parameterModes, output)
      case 99 => new TerminalOperation(pointer, intcodes, parameterModes)
    }
  }
}
