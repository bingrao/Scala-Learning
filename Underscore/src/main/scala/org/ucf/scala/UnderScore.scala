package org.ucf.scala

class UnderScore {
  /**
    * Existential types
    * @param l
    * @return
    */
  def foo(l: List[Option[_]]) = ???

  /**
    * Higher kinded type parameters
    * @param a
    * @tparam K
    * @tparam T
    */
  case class A[K[_],T](a: K[T])

  /**
    * Ignored variables
    */
  val _ = 5

  /**
    * Ignored parameters
    */
  List(1, 2, 3) foreach { _ => println("Hi") }

  /**
    * Ignored names of self types
    */
  trait MySeq { _: Seq[_] => }


  /**
    * Wildcard patterns
    */
  Some(5) match { case Some(_) => println("Yes") }

  /**
    * imports
    */
//  import java.util._ //Wildcard imports
//  import java.util.{ArrayList => _, _} // Hiding imports
//  import scala.util.matching._ // imports all the classes in the package matching
//  import com.test.Fun._  // imports all the members of the object Fun. (static import in java)
//  import com.test.Fun.{ Foo => Bar , _ } // // imports all the members of the object Fun but renames Foo to Bar
//  import com.test.Fun.{ Foo => _ , _ } // imports all the members except Foo. To exclude a member rename it to _


  /**
    * Joining letters to punctuation
    * @param x
    * @return
    */
  def bang_!(x: Int) = 5

  /**
    * Assignment operators
    *
    * @param x
    */
  def foo_=(x: Int) { ??? }

  /**
    * Placeholder syntax
    */
  List(1, 2, 3) map (_ + 2)

  /**
    * Partially applied functions
    *
    * scala> println _
    * res2: () => Unit = <function0>
    */
  List(1, 2, 3) foreach println _
  def fun = ???
  //Scala is a functional language. So we can treat function as a normal variable.
  // If you try to assign a function to a new variable, the function will be invoked
  // and the result will be assigned to the variable. This confusion occurs due to
  // the optional braces for method invocation. We should use _ after the function
  // name to assign it to another variable.
  val funLike = fun _ // funLike: () => Nothing = <function0>


  def add(a:Int, b:Int) = a + b // add: (a: Int, b: Int)Int

  // Using "_" to represent the input parameter of subAdd and subAd
  def subAdd:Int => Int = add(3, _)
  val subAd :Int => Int = add(3, _)

  /**
    * Converting call-by-name parameters to functions
    * @param callByName
    * @return
    */
  def toFunction(callByName: => Int): () => Int = callByName _ // toFunction: (callByName: => Int)() => Int

  /**
    * Anonymous functions
    * @return
    */
  def a = (x:Int) => x + 1  // a: Int => Int

  /**
    * In this case, the underscore is expanded and behaves exactly the same as it did in our
    * original anonymous function. Unfortunately, this particular use of the underscore needs
    * to be dressed up a little bit with some type information. Since Scala is statically
    * typed it needs to know ahead of time if that underscore is supposed to represent an
    * Int or a String or some other random thing that has the + method defined on it. See,
    * for example, how the type we assign to our humble underscore leads to two very
    * different functions:
    * @return
    */
  def b = (_:Int) + 1 // b: Int => Int
  val g = (_:Int) + 1  // g: Int => Int = <function1>
  val f = (_:String) + 1 // f: String => String = <function1>


  // an anonymous function with two or more input parameters

  // f2: (Int, Int) => String = <function2>
  val f2 = "The first int is " + (_:Int) + " and the second int is " + (_:Int)
  // f2: (Int, Int) => String
  def g2 = "The first int is " + (_:Int) + " and the second int is " + (_:Int)


  /**
    * Pattern Matching
    * @param x
    * @return
    */
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "anything other than one and two" // _ acts like a wildcard
  }

  /**
    * Properties
    * In scala, a getter and setter will be implicitly defined for all non-private var in a object.
    * The getter name is same as the variable name and _= is added for setter name
    */
  class Test {
    private var a = 0
    def age = a
    def age_=(n:Int) = {
      require(n>0)
      a = n
    }
  }
  val t = new Test
  t.age = 5
  println(t.age)


  /**
    * Example showing why foo(_) and foo _ are different
     */
  trait PlaceholderExample {
    def process[A](f: A => Unit)

    val set: Set[_ => Unit]

    /**
      * "process _" represents a method; Scala takes the polymorphic method and attempts to make it
      * monomorphic by filling in the type parameter, but realizes that there is no type that can
      * be filled in for A that will give the type (_ => Unit) => ? (Existential _ is not a type).
      */
//    set.foreach(process _) // Error

    /**
      * process(_) is a lambda; when writing a lambda with no explicit argument type, Scala infers
      * the type from the argument that foreach expects, and _ => Unit is a type (whereas just
      * plain _ isn't), so it can be substituted and inferred
      */
    set.foreach(process(_)) // No Error
  }

}
