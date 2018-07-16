package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

class ClassesSpec extends FunSuite with Matchers {
  test(
    """Create a class, and the class should be
      |  instantiable with the elements, but without a val
      |  I cannot get information. Having the ability to set
      |  the function for date is wonderful
      |  for unit testing. You can also set the constructor
      |  to protected""".stripMargin) {
    val stamp = Stamp("Jimi Hendrix", 2014)
    stamp.name should be ("Jimi Hendrix")
    stamp.year should be (2014)
    stamp.age should be (4) //for a limited time only
  }

  test("""Now a unit test""".stripMargin) {
    val stamp = new Stamp("Jimi Hendrix", 2014, () => 2018)
    stamp.name should be ("Jimi Hendrix")
    stamp.year should be (2014)
    stamp.age should be (4)
  }

  test(
    """Case classes have automatic functionality for getters, toString,
          equals, hashCode, apply,
          and basic pattern matching""".stripMargin) {
    val computer = Computer("Commodore", "64", 1983)
    val computer2 = computer.copy(model ="128", year=1986)
    computer2.year should be (1986)
    computer.year should be (1983)
  }

  test("Preconditions can be made with require and are used in the class") {
    val exception = the [IllegalArgumentException] thrownBy {
      val stamp = Stamp("", 1776)
    }
    exception.getMessage should be ("requirement failed: Name cannot be empty")
  }

  test("Subclassing in Scala") {
    new SportsCard(1985, "Topps", "Ken Griffey Jr.")
  }

  test("Abstract Classes in Scala") {
    pending
  }

  test("Generic Classes in Scala") {
    pending
  }

  test("Generic Classes in Scala with our own map") {
    pending
  }
}
