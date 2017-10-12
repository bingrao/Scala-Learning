package org.ucf.scala.patternmatching

abstract class Expr
case class Var(name:String) extends Expr
case class Number(num:Int) extends Expr
case class UnOp(operator:String, arg:Expr) extends Expr
case class BinOp(operator:String, left:Expr, right:Expr) extends Expr


object CaseClass{
  def main(args: Array[String]): Unit = {
    /** 1
      * It adds a factory method "apply" with the name of the class to take care of
      * object construction
      */
    val v = Var("x")
    val op = BinOp("+", Number(1), v)

    /** 2
      * All arguments in the parameter list of case classes implicitly get a val prefix,
      * so they are maintained as fields but they cannot be assigned.
      */
    val p = v.name
    val o = op.left

    /** 3
      * The compiler adds "natural" implementations of methods "toString", "hashCode", "equals" to your class
      */
    def ccToString = op.toString  // Just println(op) because "toString" function
    def isEual = op.right.equals(Var("x")) // try "==" directly because of "equals" function, so comparing two class via their ref.
    def hashcode = op.hashCode() // "hashCode" function

    /** 4
      *  The compiler adds a "copy" method to your class for making modified copies.
      *  if you want to create a standard class without noisy initial process, you can use this
      *  feature to duplicate one with a tiny modification.
      */
    val subOp = op.copy(operator = "-")
  }
}
