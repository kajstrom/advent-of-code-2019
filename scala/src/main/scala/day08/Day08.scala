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

      println("Part 1: " + (layerWithLeastZeros.count(_ == '1') * layerWithLeastZeros.count(_ == '2')))

      val layersAsLists = layers.map(_.toList)

      val image = 0.until(pixelsPerLayer).map(pixelId => {
        val pixelLayers = layersAsLists.map(_(pixelId))

        pixelLayers.filter(layer => layer != '2')
          .head
      })

      var remainingImagePixels = image.toList
      var text = ""

      while (remainingImagePixels.length > 0) {
        text += remainingImagePixels.take(25).mkString + "\r\n"
        remainingImagePixels = remainingImagePixels.drop(25)
      }

      println("Part 2:")
      print(text.replace('0', ' '))
    })
  }
}
