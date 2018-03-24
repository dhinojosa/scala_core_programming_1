package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

class MethodsSpec extends FunSuite with Matchers{
   test("In review, a method is structured like the following:") {
     def foo(x:Int, y:Int):Int = {
         x + y
     }
     foo(4, 5) should be (9)
   }

  test("Also a method can be inlined if there is only one statement:") {
    def foo(x:Int, y:Int):Int = x + y
    foo(4, 5) should be (9)
  }
}
