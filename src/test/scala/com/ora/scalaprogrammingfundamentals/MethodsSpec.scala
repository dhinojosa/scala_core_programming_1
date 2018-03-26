package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

import scala.annotation.tailrec

class MethodsSpec extends FunSuite with Matchers {
  test("In review, a method is structured like the following:") {
    pending
  }

  test("Also a method can be inlined if there is only one statement:") {
    pending
  }

  test(
    """Methods can be embedded, in case one method is
      |  exclusively only being used by another""".stripMargin) {
    pending
  }

  test(
    """Recursion is supported just like another language, here
      |  is a long way attempt to do division in a
      |  recursive style""".stripMargin) {
    pending
  }

  test(
    """Multi-parameter lists are groups or argument lists,
      |  the purpose are two fold: The get group like terms, and
      |  they make it easy to be partially applied""".stripMargin) {
     pending
  }

  test(
    """Partial Applications with a multi-parameter list
      | can be knocked out to provide only some of the entries, entries
      | that you can fill in later""".stripMargin) {

    pending
  }

  test(
    """In multi-parameter lists you can use a function. Typically
      |  the function is in the last parameter group, but it's your code,
      |  you can put it wherever you please""".stripMargin) {
    pending
  }

  test(
    """You can also use functions as arguments in whatever
      |  parameter list group that you want. But being the nature of a function,
      |  a multiline function can be a block.""".stripMargin) {
    pending
  }

  test("""What happens if I have a function as the last group in a
      | multi parameter list and that function has no parameters?""".stripMargin) {

     pending
  }


  test(
    """The above was ugly, so let's clean it up with
      |  a by-name parameter!""".stripMargin) {

    pending
  }


  test("""Turning an method into a function""") {
    pending
  }

  test(
    """Repeated parameters are the equivalent of varargs in Java, they
      |  allow additional parameters and inside the method they
      |  are just a collection called WrappedArray""".stripMargin) {

     pending
  }

  test(
    """Repeated parameters can be sent a list or any other collection,
      |  but the problem is what happens when we just send collection
      |  it would treat it as a single unit instead you can expand the units
      |  with a :_*""".stripMargin) {

    pending
  }

  test(
    """Default methods have just methods that have a value
      |  in case you don't have one at the moment.
      |  Another item you'll see with this example
      |  is the named parameter. You can set a parameter
      |  explicitly by the name to avoid any confusion as to what you
      |  are setting""".stripMargin) {

    pending
  }
}
