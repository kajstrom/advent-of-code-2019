package day06

import scala.collection.mutable.ListBuffer

class SpaceObject(identifier: String) {
  val id: String = identifier
  private val orbits: ListBuffer[SpaceObject] = ListBuffer.empty

  def setOrbitingObject(spaceObject: SpaceObject): Unit = {
    orbits.addOne(spaceObject)
  }

  def countOrbits(): Int = {
    val direct = orbits.size

    val indirect = orbits.map(spaceObject => {
      spaceObject.countOrbits()
    }).sum

    direct + indirect
  }
}
