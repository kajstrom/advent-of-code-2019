package day04

object Day04 {
  def part1(): Unit = {
    var validCount = 0

    for (password <- 284639 until 748759) {
      val passwordStr = password.toString

      var double = false
      var decreases = false

      for (i <- 0 to 4) {
        if (passwordStr(i) == passwordStr(i + 1)) {
          double = true
        }

        if (passwordStr(i) > passwordStr(i + 1)) {
          decreases = true
        }
      }

      if (double && !decreases) {
        validCount += 1
      }
    }

    println("Day 04, part 1: " + validCount)
  }

  def part2(): Unit = {
    var validCount = 0

    for (password <- 284639 until 748759) {
      val passwordStr = password.toString

      var double = false
      var decreases = false

      for (i <- 0 to 4) {
        if (passwordStr(i) == passwordStr(i + 1)) {
          val beforeIdx = i - 1
          val afterIdx = i + 2
          var notPartOfALargerGroup = true

          if (beforeIdx >= 0 && passwordStr(i) == passwordStr(beforeIdx)) {
            notPartOfALargerGroup = false
          }

          if (afterIdx <= 5 && passwordStr(i) == passwordStr(afterIdx)) {
            notPartOfALargerGroup = false
          }

          if (!double && notPartOfALargerGroup) {
            double = true
          }
        }

        if (passwordStr(i) > passwordStr(i + 1)) {
          decreases = true
        }
      }

      if (double && !decreases) {
        validCount += 1
      }
    }

    println("Day 04, part 2: " + validCount)
  }

  def main(args: Array[String]): Unit = {
    part1()
    part2()
  }
}
