package day06

import scala.collection.mutable
import scala.io.Source

object Day06 {

  def main(args: Array[String]): Unit = {
    val spaceObjects: mutable.Map[String, SpaceObject] = mutable.Map.empty

    Source.fromResource("day06.txt").getLines.toList.map(line => {
      val lineStrs = line.split("\\)")

      if (!spaceObjects.contains(lineStrs(0))) {
        spaceObjects.put(lineStrs(0), new SpaceObject(lineStrs(0)))
      }

      if (!spaceObjects.contains(lineStrs(1))) {
        spaceObjects.put(lineStrs(1), new SpaceObject(lineStrs(1)))
      }

      spaceObjects.get(lineStrs(1)).get.setOrbitingObject(spaceObjects.get(lineStrs(0)).get)
    })


    println("Part 1: " + spaceObjects.values.map(_.countOrbits).sum)

    // Find path YOU -> COM
    val you = spaceObjects.get("YOU").get
    val youToComPath = you.pathToCom()

    // Find path SAN -> COM
    val san = spaceObjects.get("SAN").get
    val sanToComPath = san.pathToCom()

    // Find first common space object
    val commonObjects = youToComPath.filter(sanToComPath.contains(_))
    val firstCommon = commonObjects.head

    // Calculate steps to transfer to same orbit
    val transfers = (you.transfersTo(firstCommon) - 1) + (san.transfersTo(firstCommon) - 1)
    println("Part 2: " + transfers)
  }
}
