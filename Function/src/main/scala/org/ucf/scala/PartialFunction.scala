package org.ucf.scala

/**
  * A function that's used in an expression and that misses some of
  * its arguments. For instance, if a function f has a type of
  * Int => Int => Int, then f and f(1):Int => Int are partially
  * applied functions.
  */

object PartialFunction {
  /**
    * This is standard method whose type if sum: (a: Int, b: Int, c: Int)Int
    * @param a
    * @param b
    * @param c
    * @return
    */
  def sum(a:Int, b:Int, c:Int) = a + b + c

  // When you invoke this sum function, you need to pass three arguments,
  // such as
  val s_1:Int = sum(1, 2, 3)

  /**
    * Usually, a partially applied function is an expression in which you
    * don't supply all of the arguments needed by the function. Instead,
    * you supply some, or none, of the needed arguments.
    * For example, you just place an underscore placeholder after "sum"
    * function, instead of three arguments, which results in a function
    * value, named as partially applied function to take three of arguments.
    *
    */
  // a: (Int, Int, Int) => Int
  val a = sum _
  val a_1 = a(1,2,3)


  /**
    * If you want to create a partial function only taking one argument based on
    * existing defined sum function.
    */
  // b: Int => Int
  val b = sum(1,_:Int, 3)
  val b_1 = b(2)


  /**
    * The principle behind the partially applied function.
    *
    * 1. When you define "val a = sum _", where the variable name "a" refers to
    * a function value object: "sum _". The function value is an instance of a
    * class <function3> generated automatically by the Scala compiler from "sum _",
    * the partially applied function expression. Here, there is a apply function in
    * the generated class to take three arguments. When you invoke a(1,2,3), actually
    * it is equal to a.apply(1,2,3).
    *
    * Similar idea here for "val b = sum (1, _, 3)", where the class is <function1>,
    * rather than <function3>. Since it only takes one arguments.
    *
    *
    * [1] You can't assign a method or nested function (local function) to a variable,
    * for example, "val c = sum", it cannot be compiled,
    *
    * [2] You can't pass a method or nested function (local function) as an argument
    * to another function, in the following example of f function, you can pass "f"
    * to the filter of someNumbers since there is a implicit conversation to convert
    * "f" to "f _".
    *
    * But you do these things if you wrap the method or nested function in a function
    * value by placing an underscore after its name. "f"  => "f _"
    *
    */

    val someNumbers = List(-1,-2, 3, -10, 5)
    // f: (x: Int)Boolean
    def f(x:Int) = x > 10
    someNumbers.filter(f) // implicit conversion to "someNumbers.filter(f _)"

}