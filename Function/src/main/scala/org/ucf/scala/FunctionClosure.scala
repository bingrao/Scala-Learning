package org.ucf.scala

object FunctionClosure {

  /**
    * In definition of a function literal, there are three kinds of source
    * for these variables used in the body of function:
    * [[1]] refer to the passed parameters, for example x, y in f
    * [[2]] refer to the variables defined in local function body, for example i in h.
    * [[3]] refer to the variables defined in outside of the function, for example sum in g
    *
    * [[Bound variable: the function literal does have a meaning in the context of function,
    *                   such as the case of [[1]] and [[2]]
    *
    * [[Free variable]]: the function literal does not itself give any information about
    *                    variable, such as the case of [[3]]. In order to make the function
    *                    work well, there should have clear definition about these variables
    *                    in the context of function.
    *
    * [[closed term]] is a function literal with only [[Bound variables]] (no free variable), such
    *                  as "(x:Int, y:Int) => x + y" and "(x:Int) => { val i = 100; x % 100 }".
    *                  Where term is a bit of source code.
    * [[open term]] is a function literal with any [[free variables]], for example of "(x:Int) => sum += x".
    *
    * Therefore, when the function value [[g]] is created at runtime from "(x:Int) => sum += x", by definition,
    * it requires that a clear binding for its free variable, sum, should be captured in context of the function
    * literal. This function value [[g]] is called a [[closure]].
    */
  var sum = 0
  val f = (x:Int, y:Int) => x + y
  val h = (x:Int) => { val i = 100; x % 100 }
  val g = (x:Int) => sum + x

  /**
    * Question: What happens if the free variable, sum, changes after the closure [[g]] is created? For example:
    *
    * scala> var sum = 1
    * sum: Int = 1
    *
    * scala> val g = (x:Int) => sum + x
    * g: Int => Int = $$Lambda$769/566698125@58516c91
    *
    * scala> g(10)
    * res1: Int = 11
    *
    * scala> sum = 100
    * mutated sum
    *
    * scala> g(10)
    * res2: Int = 110
    *
    * As we can see here, the closure [[g]] capture variable sum itself, no the value to
    * the which variable refer to. The more concrete example is followed. The free variable
    * ,total, is defined outside the closure.
    */
  val someNumbers = List(-11, -10, -5, 0, 5, 10)
  var total = 0
  // a roundabout way to sum the numbers in a List
  someNumbers.foreach(total += _)   // (x:Int) => total += x
                                    // total is a free variable


  /**
    * Question: what if a closure accesses some variable that has several different copies as
    * the program runs?
    * Answer: The instance used is the one that was active at the time the closure was created.
    *
    * In the following example, "makeIncreaser" will create a new closure with different value
    * of input parameter more. Such inc1 and inc999. Each closure will access the more variable
    * that was active when the closure was created.
    */

  // makeIncreaser: (more: Int)Int => Int
  def makeIncreaser(more:Int) = (x:Int) => x + more


  /**
    * When you call [[makeIncreaser(1)]], a closure [[inc1]] is created and returned that captures
    * the value 1 as the bonding for the free variable [[more]]. Similarly to the closure [[inc999]]
    *
    * inc1: Int => Int
    */
  val inc1 = makeIncreaser(1)
  inc1(1) // 2

  // inc999: Int => Int
  val inc999 = makeIncreaser(999)
  inc999(1) // 1000
}
