package org.ucf.scala
/**
  * Where implicits are tried. There are three places implicits are used in the language
  *   1. conversions to an expected type
  *   2. conversions of the receiver of a selection
  *   3. implicit parameters
  */
class UsageOfImplicits {

  /**
    * 1. conversions to an expected type let you use one type in a context where a different type is expected.
    * Whenever the compiler sees an X, but needs a Y, it will look for an implicit function that converts X to Y.
    */
  implicit def doubleToInt(x: Double) = x.toInt
  // What happens here is that the compiler sees a Double, specifically 3.5, in a context where it requires an Int.
  // So far, the compiler is looking an ordinary type error. Before giving up, it searches for an implicit conversion
  // from Double to Int, that is "doubleToInt".
  val i: Int = 3.5 // val i:Int = doubleToInt(3.5)

  /**
    * 2. conversions of the receiver of a selection let you adapt the receiver of a method call, i.e.,
    * the object on which a method is invoked, if the method is not applicable on the original type.
    * This kind of implicit conversion has two main uses
    *    1) Receiver conversions allow smoother integration of a new class into an existing class hierarchy
    *    2) They support writing domain speific languages (DSLs) within the language.
    *
    * for example: obj.doIt, and obj does not have a member named doIt. The compiler will try to insert conversions
    * to apply to the receiver (obj) before giving up. At this time, the compiler will act as if the expected "type"
    * of obj were "has a member named doIt".
    */

  //Interoperating with new types
  class Rational(n:Int, d:Int) {
    def +(that:Rational):Rational = ???
    def +(that:Int):Rational = ???
  }
  implicit def intToRational(x: Int) = new Rational(x,1)
  val oneHalf = new Rational(1, 2)

  // What happens behind the scenes here is that Scala compiler first tries to type check the expression "1 + oneHalf"
  // as it is. This fails because Int has several + methods, but none that takes a Rational argument. Before giving up,
  // the compiler searches for an implicit conversion from Int to another type that has a + method which can be applied
  // to a Rational.
  val tmp = 1 + oneHalf // intToRational(1) + oneHalf


  //Simulating new syntax.
  val mapData = 1 -> "One" //  any2ArrowAssoc(1) -> "one", similar with Map(1 -> "one")
  /*
  When you write 1 > "one", the compiler inserts a conversion from 1 to ArrowAssoc so that the > method can be found
  Here are the relevant definitions
    package scala
    object Predef {
      class ArrowAssoc[A](x: A) {
        def > [B](y: B): Tuple2[A, B] = Tuple2(x, y)
      }
      implicit def any2ArrowAssoc[A](x: A): ArrowAssoc[A] =
        new ArrowAssoc(x)
      ...
    }
    */
  /**
    * Whenever you see someone calling methods that appear not to exist in the receiver class, they are probably
    * using implicits. Similarly, if  you see a class named RichSomething, e.g., RichInt or RichBoolean, that
    * class is likely adding syntax-like methods to type Something.
    *
    * As you can now see, these rich wrappers apply more widely, often letting you get by with an internal DSL
    * defined as a library where programmers in other languages might feel the need to develop an external DSL
  */


  /**
    * 3. implicit parameters, on the other hand, are usually used to provide more information to the called function
    * about what the caller wants, which means the compiler could insert implicits within argument lists when we define
    * a function. The compiler will sometimes replace "somecall(a)" with "somecall(a)(b)", or "new SomeClass(a)" with
    * "new SomeClass(a)(b)", thereby adding a missing parameter list to complete a function call.
    *
    */

  class PreferredPrompt(val preference: String)
  class PreferredDrink(val preference: String)
  object Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt) {
      println("Welcome, "+ name +". The system is ready.")
      println(prompt.preference)
    }
    //Note that the implicit keyword applies to an entire parameter list, not to individual parameters
    def greet(name: String)(implicit prompt: PreferredPrompt,
                            drink: PreferredDrink) {
      println("Welcome, "+ name +". The system is ready.")
      print("But while you work, ")
      println("why not enjoy a cup of "+ drink.preference +"?")
      println(prompt.preference)
    }
  }

  object JoesPrefs {
    //To let the compiler supply the parameter implicitly, you must first define a variable of the expected type
    implicit val prompt = new PreferredPrompt("Yes, master> ")
    implicit val drink = new PreferredDrink("tea")
  }


  /**
    * implicit class
    */


  /*
  implicit class RichInt(n: Int) extends Ordered[Int] {
    def min(m: Int): Int = if (n <= m) n else m
  }

  ==>

  class RichInt(n: Int) extends Ordered[Int] {
    def min(m: Int): Int = if (n <= m) n else m
  }
  implicit final def RichInt(n: Int): RichInt = new RichInt(n)
  */
}
