package day03

import org.scalatest._

import scala.collection.mutable.ListBuffer

class Day03Spec extends FlatSpec with Matchers{
  "move" should "append coords to wire list" in {
    Day03.move("R5", ListBuffer(Coords(0,0))) should be (ListBuffer(Coords(0, 0), Coords(1, 0), Coords(2, 0), Coords(3, 0), Coords(4, 0), Coords(5, 0)))
  }

  "mapWirePath" should "produce correct path" in {
    Day03.mapWirePath(List("R2", "U2", "L2")).last should be (Coords(0, 2))
  }
}
