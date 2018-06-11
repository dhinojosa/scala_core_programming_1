package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

class TraitsSpec extends FunSuite with Matchers {
  test("A trait is analogous to an interface in Java") {
    trait Vehicle {
      def increaseSpeed(ms: Int): Vehicle

      def decreaseSpeed(ms: Int): Vehicle

      def currentSpeedMetersPerHour: Int
    }

    class Bicycle(val currentSpeedMetersPerHour: Int) extends Vehicle {
      override def increaseSpeed(ms: Int): Vehicle =
        new Bicycle(currentSpeedMetersPerHour + ms)

      override def decreaseSpeed(ms: Int): Vehicle =
        new Bicycle(currentSpeedMetersPerHour - ms)
    }

    new Bicycle(1)
      .increaseSpeed(3)
      .decreaseSpeed(1)
      .currentSpeedMetersPerHour should be(3)
  }

  test(
    """Just like Java 8 interfaces, you can have concrete
      |  methods (known as default methods in Java)""".stripMargin) {
    trait Vehicle {
      def increaseSpeed(ms: Int): Vehicle

      def decreaseSpeed(ms: Int): Vehicle

      def currentSpeedMetersPerHour: Int

      final def currentSpeedMilesPerHour: Double = currentSpeedMetersPerHour *
        0.000621371
    }

    class Bicycle(val currentSpeedMetersPerHour: Int) extends Vehicle {
      override def increaseSpeed(mh: Int): Vehicle =
        new Bicycle(currentSpeedMetersPerHour + mh)

      override def decreaseSpeed(mh: Int): Vehicle =
        new Bicycle(currentSpeedMetersPerHour - mh)
    }

    new Bicycle(4).currentSpeedMilesPerHour should be(0.002 +- .005)
  }

  test("Traits are specifically called that just for mixing in functionality") {
    val stamp = Stamp("Jimi Hendrix", 2014)
    stamp.whoAmI_?() should be("Stamp")
  }

  test("You can extends from a trait that was not built in to begin with") {

  }


  test("Extending a trait in an object to create a counter.") {

    trait Counter {
      var counter = 0
      def incrementCounter = counter = counter + 1
    }

    class Country(name:String) {
      def currentCountOfAll:Int = Country.counter

    }
    object Country extends Counter


    val country1 = new Country("Germany")
    val country2 = new Country("Zaire")
    val country3 = new Country("China")
    val country4 = new Country("India")

    Country.incrementCounter
    Country.incrementCounter
    Country.incrementCounter
    Country.incrementCounter

    country4.currentCountOfAll should be (4)
  }

  test("extends vs. with") {
    val baseballCard =
      new BaseballCard(1998, "Topps", "Ken Griffey Jr.", "Seattle", "Mariners")
    baseballCard.whoAmI_?() should be("BaseballCard")
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

    list should contain inOrder(
      "Creating C1",
      "Instantiated T1",
      "Instantiated T2",
      "Instantiated T3",
      "Created C1")
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
    myQueue.get should be(10)
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

    val car = new Car("Peugeot", "308", 0)
    car.currentSpeed should be(0)
    car.make should be ("Peugeot")
  }
}
