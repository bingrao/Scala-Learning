package org.ucf.scala

import java.io.File

/**
 * Scala does not have many built-in control abstractions since it gives you
 * the ability to create your own using these built-in control and function
 * values.
 *
 * A function is separated into two parts:
 * [[common part]] is the snippet code that are the same in every invocation of
 *                 the function.  For example, the the body of a function.
 * [[non-common part]] is the partial code which may vary from one function
 *                     invocation to the next. For example, these variables
 *                     supplied by passed-in arguments.
 *
 * High-order function, taking functions as parameters, give you extra
 * opportunities to condense and simplify code. Besides that, it also enable
 * you create control abstractions that allow you to reduce code duplication.
 *
 */
object HighOrderFunction {
  private def filesHere = (new File(".")).listFiles()
  def filesMatch(matcher: String => Boolean) = {
    /**
     * Here matcher is a function value and its type is "String => Boolean"
     * If there are two input string parameters, and return value is a Boolean,
     * then the type of function value is "(String, String) => Boolean"
     */
    for (file <- filesHere if matcher(file.getName))
      yield file
  }

  def fileEnding(query:String) = filesMatch(_.endsWith(query))

  def filesContaining(query:String) = filesMatch(_.contains(query))

  def filesRegex(query:String) = filesMatch(_.matches(query))
}
