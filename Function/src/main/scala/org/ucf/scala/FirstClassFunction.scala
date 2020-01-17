package org.ucf.scala

/**
 * In computer science, a programming language is said to have first-class functions
 * if it treats functions as first-class citizens. This means the language supports
 * passing functions as arguments to other functions, returning them as the values
 * from other functions, and assigning them to variables or storing them in data
 * structures.[1] Some programming language theorists require support for anonymous
 * functions ([[function literals]]) as well
 */
object FirstClassFunction{

  /**
   * The syntax of a function literal
   *
   *
   * sum: a functional value (Int, Int) => Int
   * (x:Int, y:Int) : function parameters, here have two Int parameters: x and y
   * => : right now sign designates that this functions converts the thing on the left (input parameters)
   *      to the thing of the right (function body)
   * { x + y} : function body, you can include multipe statments in this body block.
   *
   * A function literal is compiled into a class that when instantiated at runtime is a
   * functional value. The distinction between function literals and function values is
   * that function literals exist in the source code, whereas function values exists as
   * objects instantiated a class of [[FunctionN]] at runtime.
   */

  val sum = (x:Int, y:Int) => {
    x + y
  }

  val someNumbers = List(-11, -10, -5, 0, 5, 10)

  someNumbers.filter((x:Int) => x > 0)  // standarm form of function literal

  someNumbers.filter((x) => x > 0) // Short forms of function literals to leave out
                                       // redundant informaiton (parameter type)
  someNumbers.filter(x => x > 0) // redundant informaiton (parameter type and parentheses)

  someNumbers.filter(_ > 0) // Underscoe as placeholders for one or more parameters,
      // so long as each parameter appears only one time within the function literal

  /**
   * Sometimes when you use underscores as placeholders for parameters, the compiler
   * might not have enough information to infer missing parameter types. For example:
   *
   * val f = _ + _
   *
   * Cannot be compiled since missing type for expanded function
   */
  // f:(Int, Int) => Int = <function2>
  val sum_ = (_:Int) + (_:Int) // same semantics with sum function

}