package day01

import utils.Utils

import scala.io.Source

object Day01 {
  def fuelForMass(mass: Int): Int = {
    (Math.floor(mass / 3) - 2).toInt
  }

  def fuelForFuel(fuel: Int): Int = {
    var fuelRemaining = fuel
    var fuelNeeded = fuel

    while (fuelRemaining > 0) {
      val fuelForFuel = fuelForMass(fuelRemaining)
      if (fuelForFuel >= 0) {
        fuelRemaining = fuelForFuel
        fuelNeeded += fuelForFuel
      } else {
        fuelRemaining = 0
      }
    }

    fuelNeeded
  }

  def main(args: Array[String]): Unit = {
    var moduleFuelByMass: List[Int] = List.empty

    Utils.time {
      moduleFuelByMass = Source.fromResource("day01.txt")
        .getLines
        .map(line => fuelForMass(line.toInt)).toList

      println("Day 1, part 1: " + moduleFuelByMass.sum)
    }

    Utils.time {
      val fuelFuel = moduleFuelByMass
        .map(fuelForFuel)

      println("Day 1, part 2:" + fuelFuel.sum)
    }


  }
}
