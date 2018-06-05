package org.ucf.scala

/**
  * Implicit definitions are those that the compiler is allowed to insert into a program
  * in order to fix any of its type errors
  *
  * For example:
  *  if "x + y" fail to type check, then compiler might change it to "convert(x) + y" where "convert"
  *  is some available implicit conversion in the context. if "convert" change "x" into something that
  *  has a "+"  method, then this change might fix a program so that it type checks and run correctly;
  *  otherwise the compiler complain the type errors to users.
  *
  *
  * Implicit conversions are governed by the following general rules
  * */

class RulesForImplicits {

  /**
    * Making Rule:  Only definitions (variable, function, object, class) marked implicit are available.
    *
    * The compiler will only select among the definitions you have explicitly marked as implicit, which
    * avoid the confusion that would result if the compiler picked random functions that happen to be in
    * scope and inserted them as "conversions".
    */
  implicit def intToString(x:Int) = x.toString

  /**
    * Scope Rule: An inserted implicit conversion must be [[in scope]] as a [[single identifier]],
    * or be associated with the source or target type of the conversion.
    *
    * if you want use some conversions in a scope, you need to use some way to bring them into that scope
    * and moreover, these implicit conversions must be as a single identifier in that scope, which means when
    * you define a implicit object, the form of conversion should be only one in that scope.
    *
    *
    * There’s one exception to the “single identifier” rule. The compiler will also look for implicit
    * definitions in the companion object of the source or expected target types of the conversion
    *
    * For example: if you expect a conversion between "Dollar" and "Euro", you can define an implicit conversion from
    * Dollar to Euro or from Euro to Dollar in the companion object of either class, Dollar or Euro. There is no need
    * to import the conversion separately into your program
    */
  class Dollar
  class Euro
  object Dollar {
    implicit def dollarToEuro(x:Dollar):Euro = ???
  }

  /**
    * One-at-a-time Rule: Only one implicit is tried. For sanity's sake, the compiler does not insert further implicit
    * conversions when it is already in the middle of trying another implicit. For example: the compiler will never
    * rewrite "x+y" to "convert1(convert2(x) + y)".
    *
    * However, it’s possible to circumvent this restriction by having implicits take implicit parameters
    */


  /**
    * Explicits-First Rule: Whenever code type checks as it is written, no implicits are attempted.
    *
    * A corollary of this rule is that you can always replace implicit identifiers by explicit ones,
    * thus making the code longer but with less apparent ambiguity.
    */


  /**
    * Naming an implicit conversion. you can define an implicit with an arbitrary name.
    * The name of an implicit conversion matters only in the following situations:
    *   1. if you want to write it explicitly in a method application
    *   2. for determining which implicit conversions are available at any place in the program, for example
    *      in the following case, you can use "import MyConversions.stringWrapper" statement to make only
    *      "stringWrapper" conversion work in that scope.
    */

  object MyConversions {
    implicit def stringWrapper(s: String): IndexedSeq[Char] = ???
    implicit def intToString(x: Int): String = ???
  }
}
