package org.ucf.scala

object CurryFunction {

  /**
   * plainOldSum: (x: Int, y: Int)Int
   *
   * It is a standard definition of sum function, which call as method
   * @param x
   * @param y
   * @return
   */
  def plainOldSum(x:Int, y:Int) = x + y


  /**
   * functionValueSum: (Int, Int) => Int
   * @return
   */
  def functionValueSum = (x:Int, y:Int) => x + y


  /**
   * curriedSum: (x: Int)(y: Int)Int
   * @param x
   * @param y
   * @return
   */
  def curriedSum(x:Int)(y:Int) = x + y


  /**
   * first: (x: Int)Int => Int
   *
   * @param x
   * @return
   */
  def first(x:Int) = (y:Int) => x + y

  /**
   * second: Int => Int = <function1>
   */
  val second = first(1)

  /**
   * second_: Int => Int
   * @return
   */
  def second_ = first(1)

  /**
   * second_curry: Int => Int = <function1>
   */
  val second_curry = curriedSum(1) _

}
