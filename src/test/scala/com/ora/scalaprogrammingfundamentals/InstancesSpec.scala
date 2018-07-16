package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

class InstancesSpec extends FunSuite with Matchers {

  test("isInstanceOf determines the instance of a type") {
    val result = 10.isInstanceOf[Int]
    result should be (true)
  }

  test("asInstanceOf converts or casts a type") {
    val result = 10.asInstanceOf[Byte]
    result should be (10.0)
    result shouldBe a [java.lang.Byte]
  }
}
