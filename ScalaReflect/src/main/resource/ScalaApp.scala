package org.ucf.scala.reflect

/**
  * @author 
  */
abstract class Expression{
  val sum:Int
}
class  Add(a:Int,b:Int) extends Expression{
  val c = a + b
  this.sum = a + b
}
