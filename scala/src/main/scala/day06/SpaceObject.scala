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

  def pathToCom(): List[SpaceObject] = {
    if (orbits.isEmpty) {
      List.empty
    } else {
      List(orbits.head) ++ orbits.head.pathToCom()
    }
  }

  def transfersTo(spaceObject: SpaceObject): Int = {
    if (spaceObject.id == id) {
      0
    } else {
      1 + orbits.head.transfersTo(spaceObject)
    }
  }

  override def toString: String = {
    id
  }
}
