package day05

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class OpCodeParameterSpec extends AnyFlatSpec with Matchers{
  "getOpCode" should "return correct opcode for instruction" in {
    OpCodeParameter(3).opCode should be (3)
    OpCodeParameter(1002).opCode should be (2)
  }

  "getParameterMode" should "return correct parameter mode for instruction" in {
    OpCodeParameter(3).getParameterMode(0) should be (0)
    OpCodeParameter(1002).getParameterMode(1) should be (1)
  }
}
