package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

class TraitsSpec extends FunSuite with Matchers {
  test("A trait is analogous to an interface in Java") {
    pending
  }

  test(
    """Just like Java 8 interfaces, you can have concrete
      |  methods (known as default methods in Java)""".stripMargin) {
    pending
  }

  test("Traits are specifically called that just for mixing in functionality") {
    pending
  }

  test("You can extends from a trait that was not built in to begin with") {
    pending
  }

  test("extends vs. with") {
    pending
  }

  test("Avoiding the diamond of death") {
    var list = List[String]()

    trait T1 {
      list = list :+ "Instantiated T1"
    }

    trait T2 extends T1 {
      list = list :+ "Instantiated T2"
    }

    trait T3 extends T1 {
      list = list :+ "Instantiated T3"
    }

    class C1 extends T2 with T3 {
      list = list :+ "Instantiated C1"
    }

    list = list :+ "Creating C1"
    new C1
    list = list :+ "Created C1"

    pending
  }

  test("Stackable traits") {

    abstract class IntQueue {
      def get(): Int

      def put(x: Int)
    }

    import scala.collection.mutable.ArrayBuffer

    class BasicIntQueue extends IntQueue {
      private val buf = new ArrayBuffer[Int]

      def get() = buf.remove(0)

      def put(x: Int) {
        buf += x
      }
    }

    trait Doubling extends IntQueue {
      abstract override def put(x: Int) {
        super.put(2 * x)
      } //abstract override is necessary to stack traits
    }

    trait Incrementing extends IntQueue {
      abstract override def put(x: Int) {
        super.put(x + 1)
      }
    }

    trait Filtering extends IntQueue {
      abstract override def put(x: Int) {
        if (x >= 0) super.put(x)
      }
    }

    val myQueue = new BasicIntQueue with Doubling with Incrementing

    myQueue.put(4)
    pending
  }

  test("Self types") {
    trait Moveable {
      def increaseSpeed(ms: Int): Moveable
      def decreaseSpeed(ms: Int): Moveable
    }

    trait Vehicle {
      self: Moveable =>
      def make: String
      def model: String
    }

    class Car(val make: String, val model: String, val currentSpeed: Int)
      extends Vehicle with Moveable {
      override def increaseSpeed(ms: Int): Moveable = new Car(make, model,
        currentSpeed + ms)

      override def decreaseSpeed(ms: Int): Moveable = new Car(make, model,
        currentSpeed + ms)
    }

    pending
  }
}
