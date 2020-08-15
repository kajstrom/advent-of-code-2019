package day08

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day08 {
  def main(args: Array[String]): Unit = {
    val pixelsPerLayer = 25 * 6
    val layers: ListBuffer[String] = ListBuffer.empty

    Source.fromResource("day08.txt").getLines().toList.map(line => {
      var remainingLine = line

      while (remainingLine.length > 0) {
        layers.append(remainingLine.take(pixelsPerLayer))
        remainingLine = remainingLine.drop(pixelsPerLayer)
      }

      val layerWithLeastZeros = layers.minBy(_.count(char => char == '0'))

      println(layerWithLeastZeros)

      println("Part 1: " + (layerWithLeastZeros.count(_ == '1') * layerWithLeastZeros.count(_ == '2')))
    })
  }
}
