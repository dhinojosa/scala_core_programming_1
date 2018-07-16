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
    val baseballCard =
      new BaseballCard(1952, "Topps",
        "Mickey Mantle", "American", "Eastern")
    baseballCard.year should be (1952)
    baseballCard.manufacturer should be ("Topps")
    baseballCard.playerName should be ("Mickey Mantle")
  }

  test("Abstract Classes in Scala") {
    val baseballCard =
      new BaseballCard(1952, "Topps",
                             "Mickey Mantle", "American", "Eastern")
    baseballCard shouldBe a [Collectible]
    baseballCard shouldBe a [BaseballCard]
  }

  test("Generic Classes in Scala") {
     val baseballCard =
        new BaseballCard(1952, "Topps",
        "Mickey Mantle", "American", "Eastern")
     val box = new Box(baseballCard)
     box.contents.year should be (1952)
     box.contents.playerName should be ("Mickey Mantle")
  }

  test("Generic Classes in Scala with our own map") {
    val baseballCard =
      new BaseballCard(1952, "Topps",
        "Mickey Mantle", "American", "Eastern")
    val baseballCardBox = new Box(baseballCard)
    val yearBox = baseballCardBox.map(bc => bc.year)
    yearBox.contents should be (1952)
  }

  test("""Lab: Create two classes. One Employee, and One Manager.
      |  The Employee should have a firstName, possibly a middleName,
      |  and possibly a last name. The operative word is possibly.
      |  The Manager is a subtype of Employee and has the same
      |  properties except that it also has a property
      |  of a list of Employees. When creating an Employee, create
      |  an API that allows for the end user to leave out the middle name.
      |  Put the Employee and Manager in the
      |  src/main/scala folder and in the
      |  com.ora.scalaprogrammingfundamentals package. In this
      |  test create two Employees and and one Manager who is charge of the
      |  Employees, assert that the number of employees is two.""".stripMargin) {
    pending
  }
}
