package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

class ClassesSpec extends FunSuite with Matchers {
  test("""Create a class, and the class should be
      |  instantiable with the elements, but without a val
      |  I cannot get information.""".stripMargin) {
    val stamp = Stamp("Jimi Hendrix", 2014)
    stamp.coverPicture should be ("Jimi Hendrix")
    stamp.year should be (2014)
    stamp.age should be (4)
  }

  test("""Case classes have automatic functionality for getters, toString,
          equals, hashCode, apply, and basic pattern matching""".stripMargin) {
    val computer = Computer("Commodore", "64", 1983)
    computer.make should be ("Commodore")
    computer.model
  }

  test("Preconditions can be made with require") {
    val exception = the[IllegalArgumentException] thrownBy {
      val stamp = Stamp("", 1776)
    }
    exception.getMessage should be ("requirement failed: Cover Picture cannot be empty")
  }

  test("Subclassing") {
    val baseballCard =
      new BaseballCard(1952, "Topps", "Mickey Mantle", "American", "Eastern")

    baseballCard.year should be (1952)
    baseballCard.manufacturer should be ("Topps")
    baseballCard.playerName should be ("Mickey Mantle")
  }

  test("Abstract Classes") {
    //Create class Collectable with year
    val baseballCard =
      new BaseballCard(1952, "Topps", "Mickey Mantle", "American", "Eastern")

    baseballCard shouldBe a [Collectable]
    baseballCard shouldBe a [BaseballCard]
  }

  test("Generic Classes") {
    val box = new Box(new BaseballCard(1952, "Topps", "Mickey Mantle", "American", "Eastern"))
    box.contents.year should be (1952)
    box.contents.playerName should be ("Mickey Mantle")
  }
}
