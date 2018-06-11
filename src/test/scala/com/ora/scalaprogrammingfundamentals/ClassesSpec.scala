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
    pending
  }

  test(
    """Use the companion object to create the stamp.
      |  This test will fail on Jan 1, 2019.
      |  This is moreso an integration""".stripMargin) {
    pending
  }

  test(
    """Case classes have automatic functionality for getters, toString,
          equals, hashCode, apply,
          and basic pattern matching""".stripMargin) {
    pending
  }

  test("Preconditions can be made with require and are used in the class") {
    pending
  }

  test("Subclassing in Scala") {
    pending
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
