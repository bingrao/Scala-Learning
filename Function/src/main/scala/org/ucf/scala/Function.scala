package org.ucf.scala

import scala.io.Source

object Method {
  /**
   * The most common way to define a function is as a member of some object;
   * such as a function is called a method. For example in the following codes,
   * there are two methods.
   */
  private def processLine(filename:String, width:Int, line:String) = {
    if (line.length > width) println(filename + ": " + line.trim)
  }

  def processFile(filename:String, width:Int) = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }
}

object LocalFunction{
  /**
   * Programs should be decomposed into many small functions that each do a well-defined
   * task. Each building task should be simple enough to be understood individually.
   * Such more helper function names leads to pollute the program namespace. The solution
   * is to fix out this issue, there are two ways:
   * 1. define a private method in a object
   * 2. define a local function in an object, just like local varibles, are visible only
   * in their enclosing block.
   */

  def processFile(filename:String, width:Int) = {

    /**
     *  As a local function, processLine is in scope inside processFile,
     *  so it can access the parameters and local variables of its enclosing
     *  function, but inaccessible outside.
     * @param line
     */
    def processLine(line:String) = {
      if (line.length > width) println(filename + ": " + line.trim)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }

}

object SpecialFunctionCall{
  /**
    * [[Repeated Parameters]]
    * Place an asterisk after the type of the parameter.
    */

  // echo: (args: String*)Unit
  def echo(a:Int, args:String*) =
    for (arg <- args) println(a + ":" + arg)

  echo(0)  // No parameter
  echo(1, "one") // Only one
  echo(2, "one", "two") // Only two

  val arr = Array("What's", "up", "doc?")

//  echo(3,arr) // does not compile since there is an error.
  echo(3,arr:_*) // "_*" append the array argument. This notation
                 // tells the compiler to pass each element of arr
                 // as its own argument to echo, rather than all
                 // of it as a single argument.

  /**
    * [[Named Arguments]]
    *
    * In a normal function call, the arguments in the call are matched
    * one by one in the order of the parameters of the called function.
    *
    * If you want to pass arguments to a function in a different order
    * you can call it with named arguments, which does not change the
    * meaning.
    */
  def speed(distance:Float, time:Float):Float = distance / time

  speed(100, 10)
  speed(distance = 100, time = 10)
  speed(time = 10, distance = 100)

  /**
    * [[Default Parameters Values]]
    *
    * Scala lets you specify default values for function parameters
    */

  def printTime(out:java.io.PrintStream = Console.out, divisor:Int = 1) =
    out.println("time = " + System.currentTimeMillis())

  printTime(divisor = 2) // using default value
  printTime(Console.err) // specify Console.err
  printTime(Console.err, 1000)


  /**
    * [[call-by-value]] VS [[call-by-name]]
    *
    * [[call-by-value]] functions compute the passed-in expression's value
    * before calling the function, thus the same value is accessed every time.
    *
    * [[call-by-name]] functions recompute the passed-in expression's value every
    * time it is accessed.
    */

  // [[call-by-name]]
  def callByName(x: => Int) = {
    println("x1 = " + x)
    println("x2 = " + x)
  }

  // [[call-by-value]]
  def callByValue(x:Int) = {
    println("x1 = " + x)
    println("x2 = " + x)
  }

  def something() = {
    println("calling something")
    10
  }

  /**
    *
    * The side-effect of the passed-in function something
    * call twice.
    * scala> callByName(something)
    * calling something
    * x1 = 10
    * calling something
    * x2 = 10
    *
    *
    * The side-effect of the passed-in function something
    * only only happened once.
    * scala> callByValue(something)
    * calling something
    * x1 = 10
    * x2 = 10
    */

}