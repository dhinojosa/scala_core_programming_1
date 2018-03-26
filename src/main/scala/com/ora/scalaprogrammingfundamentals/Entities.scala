package com.ora.scalaprogrammingfundamentals

import java.time.LocalDate

class Stamp protected(val coverPicture: String,
                      val year: Short,
                      currentYear: () => Short) extends Introspection {
  def age: Int = currentYear() - year
}

object Stamp {
  def apply(coverPicture: String, year: Short): Stamp = {
    require(coverPicture.nonEmpty, "Cover Picture cannot be empty")
    require(year >= 1840, "Stamps were not available until 1840")
    new Stamp(coverPicture, year, () => LocalDate.now.getYear.toShort)
  }
}

abstract class Collectable {
  def year:Int
}

class SportsCard(val year: Int, val manufacturer: String, val playerName: String) extends Collectable

class BaseballCard(year: Int,
                   manufacturer: String,
                   playerName: String,
                   val league: String,
                   val division: String) extends
  SportsCard(year: Int, manufacturer: String, playerName: String)
  with Introspection

case class Computer(make: String, model: String, year: Short)

class Box[A](val contents:A)
