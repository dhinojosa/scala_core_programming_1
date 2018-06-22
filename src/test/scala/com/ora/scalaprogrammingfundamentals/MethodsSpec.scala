package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

import scala.annotation.tailrec

class MethodsSpec extends FunSuite with Matchers {
  test("In review, a method is structured like the following:") {
    def foo(x: Int): Int = {
      x + 1
    }

    foo(4) should be(5) //ScalaTest assertion
  }

  test("Also a method can be inlined if there is only one statement:") {
    def foo(x: Int): Int = x + 1

    foo(4) should be(5)
  }

  test(
    """Methods can be embedded, in case one method is
      |  exclusively only being used by another""".stripMargin) {
    def foo(x: Int, y: Int): Int = {
      def bar(): Int = x + y + 10

      bar()
    }

    foo(4, 10) should be(24)
  }

  test(
    """Recursion is supported just like another language, here
      |  is a long way attempt to do division in a
      |  recursive style""".stripMargin) {

    def divide(numerator: Int, denominator: Int): Option[Int] = {
      def divideHelper(num: Int, count: Int): Option[Int] =
        if (num < denominator) Some(count)
        else divideHelper(num - denominator, count + 1)

      if (denominator == 0) None
      else divideHelper(numerator, 0)
    }

    divide(1, 0) should be(None)
    divide(1, 1) should be(Some(1))
    divide(4, 2) should be(Some(2))
    divide(10, 2) should be(Some(5))
    divide(9, 3) should be(Some(3))
    divide(5, 5) should be(Some(1))
  }

  test(
    """Multi-parameter lists are groups or argument lists,
      |  the purpose are two fold: The get group like terms, and
      |  they make it easy to be partially applied, another reason is
      |  for implicits""".stripMargin) {
    def foo(x:Int)(y:Int)(a:String, b:String) = {
      a + (x + y) + b
    }

    foo(3)(5)("<<<", ">>>") should be ("<<<8>>>")
  }

  test(
    """Partial Applied Function with a multi-parameter list
      |  can be knocked out to provide only some of the entries, entries
      |  that you can fill in later""".stripMargin) {

    def foo(x:Int)(y:Int)(a:String, b:String) = {
      a + (x + y) + b
    }

    val fun: (String, String) => String = foo(4)(3)_

    fun("$$$", "$$$") should be ("$$$7$$$")
    fun("***", "***") should be ("***7***")
  }

  test(
    """In multi-parameter lists you can use a function. Typically
      |  the function is in the last parameter group, but it's your code,
      |  you can put it wherever you please""".stripMargin) {

    def foo(x:Int, y:Int)(f:Int => String) = f(x * y)
    val f1 = (v1:Int) => s"The value is $v1"
    foo(40, 10)(f1) should be ("The value is 400")
  }

  test(
    """You can also use functions as arguments in whatever
      |  parameter list group that you want. But being the nature of a function,
      |  a multiline function can be a block.""".stripMargin) {


    def foo(x:Int, y:Int)(f:Int => String) = f(x * y)

    val result = foo(40, 10) { i =>
      val add3 = i + 3
      s"The value is $add3"
    }

    result should be ("The value is 403")
  }

  test(
    """What happens if I have a function as the last group in a
      |  multi parameter list and that function has no parameters?"""
      .stripMargin) {

    def timer[A](f:() => A) = {
      val startTime = System.currentTimeMillis()
      val result = f()
      val endTime = System.currentTimeMillis()
      (endTime - startTime, result)
    }

    val t = timer(() => {
      Thread.sleep(4000)
      50 + 10
    })

    val time = t._1
    val item = t._2

    time should (be >= 4000L and be <= 5000L)
    item should be (60)
  }


  test(
    """The above was ugly, so let's clean it up with
      |  a by-name parameter!""".stripMargin) {

    def timer[A](f: => A) = {
      val startTime = System.currentTimeMillis()
      val result = f
      val endTime = System.currentTimeMillis()
      (endTime - startTime, result)
    }

    val t = timer {
      Thread.sleep(4000)
      50 + 10
    }

    val time = t._1
    val item = t._2

    time should (be >= 4000L and be <= 5000L)
    item should be (60)
  }


  test("""Turning an method into a function""") {
    def foo(x:Int, y:Int, z:Int) = x + y + z
    val f: (Int, Int, Int) => Int = foo _
    f(3, 5, 10) should be (18)

    def myApply[T](x:T, y:T)(implicit ord:Ordering[T]) =
      if (ord.gt(x, y)) x else y

    def myApply2[T <: Ordered[T]](x:T, y:T) =
      if (x < y) x else y
  }

  test(
    """Repeated parameters are the equivalent of varargs in Java, they
      |  allow additional parameters and inside the method they
      |  are just a collection called WrappedArray""".stripMargin) {

    def zoom[A, B](a:A, rest:B*) = {
      s"a:$a, rest:$rest"
    }

    zoom(3, "Hello", "World", "Scala") should be
    ("a:3, rest:WrappedArray(Hello, World, Scala)")
  }

  test(
    """Repeated parameters can be sent a list or any other collection,
      |  but the problem is what happens when we just send collection
      |  it would treat it as a single unit instead you can expand the units
      |  with a :_*""".stripMargin) {
    def zoom[A, B](a:A, rest:B*) = {
      s"a:$a, rest:$rest"
    }

    zoom(3, List("Hello", "World", "Scala")) should be
    ("a:3, rest:WrappedArray(List(Hello, World, Scala))")

    zoom(3, List("Hello", "World", "Scala"):_*) should be
    ("a:3, rest:List(Hello, World, Scala)")
  }

  test(
    """Default parameters have just methods that have a value
      |  in case you don't have one at the moment.
      |  Another item you'll see with this example
      |  is the named parameter. You can set a parameter
      |  explicitly by the name to avoid any confusion as to what you
      |  are setting""".stripMargin) {
//    def foo(x:Int)(y:Int)(a:String = "##", b:String = "##") = {
//      a + (x + y) + b
//    }
//
//    foo(3)(4)_ should be ("##7##")

    def bar(x:Int, y:Int, a:String = "##", b:String = "##") = {
      a + (x + y) + b
    }

    bar(10, 20) should be ("##30##")
  }
}
