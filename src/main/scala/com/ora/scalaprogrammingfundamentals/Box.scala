package com.ora.scalaprogrammingfundamentals

class Box[A](val a:A) {
  def map[B](f: A => B):Box[B] = new Box(f(a))
}
