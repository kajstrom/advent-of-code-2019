package day03

import utils.Utils

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day03 {
  def move(movement: String, wire: ListBuffer[Coords]): ListBuffer[Coords] = {
    val direction = movement(0)
    val moves = movement.substring(1).toInt
    val extendedWire = wire.to(ListBuffer)

    var latestCoords = extendedWire.last
    for(i <- 0 until moves) {
      latestCoords = direction match {
        case 'R' => latestCoords.right
        case 'L' => latestCoords.left
        case 'U' => latestCoords.up
        case 'D' => latestCoords.down
      }

      extendedWire.addOne(latestCoords)
    }

    extendedWire
  }

  def mapWirePath(movements: List[String]): ListBuffer[Coords] = {
    var fullWire = ListBuffer(Coords(0,0))

    for (movement <- movements) {
      fullWire = move(movement, fullWire)
    }

    fullWire
  }

  def findCrossings(wireA: ListBuffer[Coords], wireB: ListBuffer[Coords]): ListBuffer[Coords] = {
    wireA.tail.intersect(wireB.tail)
  }

  def manhattanDistance(a: Coords, b: Coords): Int = {
    Math.abs(a.x - b.x) + Math.abs(a.y - b.y)
  }

  def part1(wireAMovements: List[String], wireBMovements: List[String]): Unit = {
    val wireA = mapWirePath(wireAMovements)
    val wireB = mapWirePath(wireBMovements)

    val crossings = findCrossings(wireA, wireB)

    val startingPoint = Coords(0, 0)

    println("Day 3, part 1: " + crossings.map(crossing => manhattanDistance(startingPoint, crossing)).min)
  }

  def part2(wireAMovements: List[String], wireBMovements: List[String]): Unit = {
    val wireA = mapWirePath(wireAMovements)
    val wireB = mapWirePath(wireBMovements)

    val closestBySteps = findCrossings(wireA, wireB)
      .map(crossing => (crossing, wireA.indexOf(crossing) + wireB.indexOf(crossing)))
      .minBy(_._2)

    println("Day 3, part 2: " + closestBySteps._2)
  }

  def main(args: Array[String]): Unit = {
    val movements = Source.fromResource("day03.txt").getLines.map(line => line.split(",")).toList
    val wireAMovements = movements(0).toList
    val wireBMovements = movements(1).toList

    Utils.time {
      part1(wireAMovements, wireBMovements)
    }

    Utils.time {
      part2(wireAMovements, wireBMovements)
    }
  }
}
