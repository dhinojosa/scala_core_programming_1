package com.ora.scalaprogrammingfundamentals

abstract class Collectible {
  def year:Int
}

class SportsCard(val year:Int,
                 val manufacturer:String,
                 val playerName:String) extends Collectible

class BaseballCard(year:Int,
                   manufacturer: String,
                   playerName:String,
                   val league:String,
                   val division:String) extends
                     SportsCard(year, manufacturer, playerName)

