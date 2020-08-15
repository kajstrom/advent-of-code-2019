package day08

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day08 {
  def main(args: Array[String]): Unit = {
    val pixelsPerLayer = 25 * 6
    var layers: List[String] = List.empty

    val line = Source.fromResource("day08.txt").getLines().toList.head
    layers = line.grouped(pixelsPerLayer).toList

    val layerWithLeastZeros = layers.minBy(_.count(char => char == '0'))

    println("Part 1: " + (layerWithLeastZeros.count(_ == '1') * layerWithLeastZeros.count(_ == '2')))

    val layersAsLists = layers.map(_.toList)

    val image = 0.until(pixelsPerLayer).map(pixelId => {
      val pixelLayers = layersAsLists.map(_(pixelId))
      pixelLayers.filter(layer => layer != '2').head
    })

    val text = image.grouped(25).map(_.mkString).mkString("\r\n")

    println("Part 2:")
    print(text.replace('0', ' '))
  }
}
