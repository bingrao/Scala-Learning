package org.ucf.scala.patternmatching
import math.{E, Pi}


/**
  * There are three differences about match (scala) and switch (java) to
  * keep in mind.
  * 1. match statement is an expression in scala, so please be careful of type safe.
  * 2. Scala's alternative expressions never "fall through" into the next case even there
  *    is no break statement.
  * 3. If there is none of patterns match, an exception named "MatchError" is  thrown, so
  *    you should have to make sure that all cases are covered even if it means adding a default
  *    case where there's nothing to no.
  */
object PatternMatching {
  def simplifyTop(expr: Expr):Expr = expr match {
    case UnOp("-", UnOp("-",e)) => e
    case BinOp("+", e, Number(0)) => e
    case BinOp("*", e, Number(1)) => e
    case _ => expr
  }

  /**
    * Wildcard patterns
    */
  def wp(expr: Expr) = expr match {
    case BinOp(op,left, right) =>
      println(expr + "is a binary operation")
    case UnOp(_, _) => // Wildcards can also be used to ignore parts of an object
      // that you do not care about.
      println( expr + " is an unary operation")
    case _ => //The wildcard pattern (_) matches any object whatsoever
  }

  /**
    * Constant patterns - A constant pattern matches only itself
    *  1. Any literal may be used as a constant
    *  2. any val and singleton object can be used as a constant.
    *  3. Constant patterns can have symbolic names (Nil, E, Pi), most of them are start with uppercase.
    *  4. If the constant is a filed of some object, you can prefix it with a qualifier,
    *     such as "this.pi", "obj.pi". (start with lowercase)
    *  5. Enclosing a variable name that starting with lowercase in back ticks, such as `pi`, as a constant
    *     -- In the scala, you can also enclose a keyword in the back ticks to treat it as an ordinary identifier.
    *         such as `yield`(), treats yield as an identifier rather than a keyword.
    *
    */
  def describe(x:Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "The empty list" // symbolic name Nil
    case Pi => "strange math? Pi = "+ Pi //symbolic name Pi
    case _ => "something else"
  }

  /**
    * Variable patterns - A variable pattern matches any object, just like a wildcard.
    * How to distinguish a variable or constant in a match statement?
    * -- A simple name starting with a lowercase letter is taken to be a pattern variable;
    * -- All other references are taken to be constants.
    */
  def vp(expr: Any) = expr match {
    case 0 => "Zero"
    case somethingElse => //Unlike a wildcard, scala binds the variable to whatever the object is.
      // Then you can use this variable to act on the object further.
      "Not zero: " + somethingElse
  }

  /**
    * Constructor patterns
    * How does it work.
    *  1. Check the object whether or not is a member of the named case class. (top-level check)
    *  2. Check the constructor parameters of the object match the extra patterns supplied (Deep matches)
    */
  def cp(expr: Expr) = expr match {
    case BinOp("+",e,Number(0)) => // three level deep check
      println("A deep match")
    case _ =>
  }

  /**
    * Sequence patterns - used for List or Array just like you match against case classes.
    *  Using the same syntax, but now you can specify any number of elements with the pattern
    *  You can specify "_*" as the last element of the pattern
    */
  def sp(expr: Any) = expr match {
    case List(0,_,_) => // check for a three-element list starting with zero
      println("found it")
    case List(0,_*) => // looking for any number of elements list staring with zero
      println("found it")
    case Array(0,1,2,_*) => // looking for any number of elements array starting with 0,1, 2
      println("found it")
    case _ =>
  }

  /**
    * Tuple patterns
    */

  def tupleDemo(expr:Any) = expr match{
    case (a,b,c) => //A pattern like (a, b, c) matches an arbitrary 3-tuple
      println("matched"+a+b+c)
    case _ =>
  }

  /**
    * Typed patterns
    * you can use a typed pattern as a convenient replacement for type tests and type casts.
    * Type cast:
    * 1. To test whether an expression expr has type String. -- expr.isInstanceOf[String]
    * 2. To cast the same expression to type String. -- expr.asInstanceOf[String]
    */
  def generalSize(x:Any) = x match{
    //Note that, even through s and x refer to the same value, the type of x is Any, but the type of s is String.
    case s:String => //The pattern variable s then refers to that string.
      s.length
    case m:Map[_,_] => //This pattern matches any value that is a Map of some arbitrary
      m.size           // key and value types and lets m refer to that value
    case _ => -1
  }

  /**
    * Variable binding
    * In addition to the standalone variable patterns, you can also add a variable to any other pattern.
    * That is you simply write the variable name, an at sign (@), and then the pattern. If the pattern succeeds,
    * set the variable to the matched object just as with a simple variable pattern.
    */
  def vb(expr: Expr) = expr match {
    case UnOp("abs", e @ UnOp("abs",_)) => // If the entire pattern match succeeds, then the portion that matched
      e                                    // the UnOp("abs",_) part is made available as variable e.
    case _ =>
  }

  /**
    * Pattern guards
    * A pattern guard comes after a pattern and starts with an if, the guard can be an arbitrary boolean expression,
    * which typically refer to variables in the pattern.
    * If a pattern guard is present, the match succeeds only if the guard evaluates to true.
    */
  def simplifyAdd(e:Any) = e match{
    case BinOp("+",x,y) if x == y =>
      BinOp("*",x,Number(2))
    case n:Int if 0 < n => e
    case s:String if s(0) == 'a' => e
    case _ => e
  }

  /**
    * Pattern overlaps
    */
  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("", e)) =>
      simplifyAll(e) // '' is its own inverse
    case BinOp("+", e, Number(0)) =>
      simplifyAll(e) // ‘0’ is a neutral element for ‘+’
    case BinOp("*", e, Number(1)) =>
      simplifyAll(e) // ‘1’ is a neutral element for ‘*’
    case UnOp(op, e) =>
      UnOp(op, simplifyAll(e))
    case BinOp(op, l, r) =>
      BinOp(op, simplifyAll(l), simplifyAll(r))
    case _ => expr
  }

  /**
    * Sealed classes
    * Whenever you write a pattern match, you need to make sure you have covered all of the possible cases, but sometimes
    * it is difficult to find all cases. The alternative is to make the superclass of your case classes sealed.
    * --
    */
}

