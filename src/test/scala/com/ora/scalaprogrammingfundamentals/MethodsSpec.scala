package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

import scala.annotation.tailrec

class MethodsSpec extends FunSuite with Matchers {
  test("In review, a method is structured like the following:") {
    def foo(x: Int, y: Int): Int = {
      x + y
    }
    foo(4, 5) should be(9)
  }

  test("Also a method can be inlined if there is only one statement:") {
    def foo(x: Int, y: Int): Int = x + y
    foo(4, 5) should be(9)
  }

  test(
    """Methods can be embedded, in case one method is
      |  exclusively only being used by another""".stripMargin) {
    def foo(x: Int, y: Int): Int = {
      def bar(z: Int): Int = {
        z + 10
      }
      bar(x + y)
    }

    foo(4, 10) should be(24)
  }

  test(
    """Recursion is supported just like another language, here
      |  is a long way attempt to do division in a
      |  recursive style""".stripMargin) {

    def divide(numerator: Int, denominator: Int): Option[Int] = {
      @tailrec
      def divideHelper(numerator: Int, denominator: Int,
                       count: Int): Option[Int] = {
        if (denominator == 0) Some(count)
        else if (numerator < denominator) Some(count)
        else {
          divideHelper(numerator - denominator, denominator, count + 1)
        }
      }

      if (denominator == 0) None
      else divideHelper(numerator, denominator, 0)
    }

    divide(1, 0) should be(None)
    divide(1, 1) should be(Some(1))
    divide(4, 2) should be(Some(2))
    divide(10, 2) should be(Some(5))
    divide(9, 3) should be(Some(3))
  }

  test(
    """Multi-parameter lists are groups or argument lists,
      |  the purpose are two fold: The get group like terms, and
      |  they make it easy to be partially applied""".stripMargin) {
     def multiParameters(w:Int)(x:Int)(y:String, z:String) = {
         y + (w + x) + z
     }
     multiParameters(10)(20)("<<", ">>") should be ("<<30>>")
  }

  test(
    """Partial Applications with a multi-parameter list
      | can be knocked out to provide only some of the entries, entries
      | that you can fill in later""".stripMargin) {

    def multiParameters(w:Int)(x:Int)(y:String, z:String) = y + (w + x) + z
    val function = multiParameters(10)(20)_
    function("{", "}") should be ("{30}")
  }

  test(
    """In multi-parameter lists you can use a function. Typically
      |  the function is in the last parameter group, but it's your code,
      |  you can put it wherever you please""".stripMargin) {
    def multiParametersWithAFunction(w:Int)(x:Int)(f: Int => String) = f(w * x)
    val result = multiParametersWithAFunction(50)(50)(i => s"The value is $i")
    result should be ("The value is 2500")
  }

  test(
    """You can also use functions as arguments in whatever
      |  parameter list group that you want. But being the nature of a function,
      |  a multiline function can be a block.""".stripMargin) {
    def multiParametersWithAFunction(w:Int)(x:Int)(f: Int => String) = f(w * x)

    val result = multiParametersWithAFunction(50)(50){i =>
      val add3 = i + 3
      s"The value is $add3"
    }
    result should be ("The value is 2503")
  }

  test("""What happens if I have a function as the last group in a
      | multi parameter list and that function has no parameters?""".stripMargin) {

     def timer[A](expectedThreshold:Long)(f:() => A):(Long, Long, A) = {
       val start = System.currentTimeMillis()
       val result = f()
       val end = System.currentTimeMillis() - start
       (end, end - expectedThreshold, result)
     }

     val (time, offset, result) = timer(3000){() =>
       Thread.sleep(3000)
       "Hello"
     }

     time should (be >= 3000L and be <= 3100L)
     offset should be <= (100L)
     result should be ("Hello")
  }


  test(
    """The above was ugly, so let's clean it up with
      |  a by-name parameter!""".stripMargin) {

    def timer[A](expectedThreshold:Long)(f: => A):(Long, Long, A) = {
      val start = System.currentTimeMillis()
      val result = f
      val end = System.currentTimeMillis() - start
      (end, end - expectedThreshold, result)
    }

    val (time, offset, result) = timer(3000){
      Thread.sleep(3000)
      "Hello"
    }

    time should (be >= (3000L) and be <= (3100L))
    offset should be < (100L)
    result should be ("Hello")
  }


  test("""Turning an method into a function""") {
    def multBy3(x:Int) = x * 3
    List(1, 2, 3).map(r => multBy3(r)) should contain inOrder (3, 6, 9)
  }

  test(
    """Repeated parameters are the equivalent of varargs in Java, they
      |  allow additional parameters and inside the method they
      |  are just a collection called WrappedArray""".stripMargin) {

    def varargs[A, B](arg1: A, rest: B*) = {
      s"arg1=$arg1, rest=$rest"
    }

    varargs("Hello", 30, 12, 40, 50) should
      be("arg1=Hello, rest=WrappedArray(30, 12, 40, 50)")
  }

  test(
    """Repeated parameters can be sent a list or any other collection,
      |  but the problem is what happens when we just send collection
      |  it would treat it as a single unit instead you can expand the units
      |  with a :_*""".stripMargin) {

    def varargs[A, B](arg1: A, rest: B*) = {
      s"arg1=$arg1, rest=$rest"
    }

    varargs("Hello", List(20, 40, 50)) should
      be("arg1=Hello, rest=WrappedArray(List(20, 40, 50))")
    varargs("Hello", List(20, 40, 50): _*) should
      be("arg1=Hello, rest=List(20, 40, 50)")
  }

  test(
    """Default methods have just methods that have a value
      |  in case you don't have one at the moment.
      |  Another item you'll see with this example
      |  is the named parameter. You can set a parameter
      |  explicitly by the name to avoid any confusion as to what you
      |  are setting""".stripMargin) {

    val taxTableFunction = (amount: Float) => {
      val table = Map(
        10000.00 -> 1150.00,
        20000.00 -> 2635.00,
        30000.00 -> 3661.00,
        40000.00 -> 5116.00,
        50000.00 -> 6676.00,
        60000.00 -> 8161.00,
        70000.00 -> 9654.00,
        80000.00 -> 11191.00)
      table.getOrElse(amount - (amount % 10000), amount * .40).toFloat
    }

    def calculateTax(income: Float,
                     standardDeduction: Float = 6350.00f,
                     taxTable: Float => Float): Float = {
      taxTable.apply(income - standardDeduction)
    }

    calculateTax(50000, taxTable = taxTableFunction) should be (5116.00)
  }
}
