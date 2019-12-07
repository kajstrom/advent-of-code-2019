package day02

import org.scalatest._

import scala.collection.mutable.ListBuffer

class Day02Spec extends FlatSpec with Matchers{
  "execute" should "return correct value for index 0" in {
    Day02.execute(ListBuffer(1, 0, 0, 0)) should be (ListBuffer(2, 0, 0, 0))
    Day02.execute(ListBuffer(2,3,0,3,99)) should be (ListBuffer(2,3,0,6,99))
    Day02.execute(ListBuffer(2,4,4,5,99,0)) should be (ListBuffer(2,4,4,5,99,9801))
    Day02.execute(ListBuffer(1,1,1,4,99,5,6,0,99)) should be (ListBuffer(30,1,1,4,2,5,6,0,99))
  }
}
