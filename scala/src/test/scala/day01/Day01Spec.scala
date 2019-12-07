package day01

import org.scalatest._

class Day01Spec extends FlatSpec with Matchers{
  "fuelForFuel" should "correctly calculate fuel needed for fuel" in {
    Day01.fuelForFuel(2) should be (2)
    Day01.fuelForFuel(654) should be (966)
  }
}
