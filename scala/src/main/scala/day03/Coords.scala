package day03

case class Coords(x: Int, y: Int) {
  def right: Coords = Coords(x + 1, y)
  def left: Coords = Coords(x - 1, y)
  def up: Coords = Coords(x, y + 1)
  def down: Coords = Coords(x, y - 1)
}
