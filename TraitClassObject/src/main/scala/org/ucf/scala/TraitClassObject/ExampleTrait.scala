package org.ucf.scala.TraitClassObject

/**
  * A trait encapsulates methods (abstract or concreted) and fields definitions, which can then be
  * reused by mixing them into classes.
  * In fact, you can do anything in a trait like that you can in a class definition  with only two exceptions:
  * 1) a trait cannot have any "class" constructed parameters
  * 2) In classes, super calls are statically bound, in traits, they are dynamically bound.
  */

trait Philosophical { // Do not declare a  superclass, so extends AnyRef by default, like a class
  def philisophize(): Unit ={  // concrete methods.
    println("I consume memory, therefore I am")
  }
}

/**
  * A  class can use "extends" or "with" keywords to mix one or more traits in the definition.
  * Scala programmers "mix in" traits rather than inherit from them, which means the implementation to invoke
  * "super" will be determined anew each time when the trait is mixed into a concrete class. that's why super
  * calls are dynamically bound, but in a class, they are statically bound because the superclass of a class
  * is determined. "extends" a trait to show that you implicitly inherit the trait's superclass.
  * If you wish to mix a trait into a class that explicitly extends a superclass, you can use "extends" to indicate
  * the superclass and "with" to mix in the trait. If you want to mix in multiple traits, you add more "with" clauses
  */
class Frog extends Philosophical { // that is it subclasses AnyRef (the superclass of Philosophical)
  override def toString: String = "green"
  val name:String = "Frog"
}
class Animal
class Ant extends Animal with Philosophical{
  override def toString = "green" //override a method declared in a superclass
  override def philisophize(): Unit = { // override a method declared in a trait
    println("It ain't easy being "+ toString +"!")
  }

}
/**
  * Also you can treat "trait" as a type, like the class. For example:
  *  val phil: Philosophical = ...  At here, "Philosophical" is a type of "phil" .
  *  So variable "phil" could have been initialized with any object whose classes mixes in Philosophical,
  *  But "phil" just can access the mixed part of "Philosophical", for example, you can use
  *  phil.name to access the "name" of "Frog"
  *
  */
class Point(val x: Int, val y: Int)
trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  def left = topLeft.x
  def right = bottomRight.x
}

/**
  * The following information is compiling information, as we can see that there are two public methods, "toLeft" and
  * "bottomRight" for the constructor parameters. So these two methods will override the methods in Rectangular trait if
  *  this class mix in a Rectangular trait.
  * <paramaccessor> private[this] val topLeft: Point = _;
  * <stable> <accessor> <paramaccessor> def topLeft: Point = Rectangle.this.topLeft;
  *  <paramaccessor> private[this] val bottomRight: Point = _;
  *  <stable> <accessor> <paramaccessor> def bottomRight: Point = Rectangle.this.bottomRight;
  *
  */
class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular


abstract class IntQueue{
  def get(): Int
  def put(x:Int)
}
import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]()
  override def get(): Int = buf.remove(0)
  override def put(x: Int): Unit = buf += x
}

/**
  * 1. it declares a superclass, IntQueue, which means this trait can only be mixed into a class that also extends IntQueue
  * 2. The trait has a super call on a method declared abstract.
  */
trait Doubling extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(2*x)
}




