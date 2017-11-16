package org.ucf.scala.reflect

import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._


object TranverseTree extends Traverser {
  import com.github.mlangc.brackets.DefaultBeautifier
  import scala.reflect.runtime.universe.{showRaw,show}
  override def traverse(tree: universe.Tree): Unit = {
    println(show(tree))
    println("\n")
    println(DefaultBeautifier.format(showRaw(tree)))
    println("*************************************************\n")
    super.traverse(tree)
  }
}



object TranverseApplyTree extends Traverser {
  var applies = List[Apply]()
  override def traverse(tree: Tree): Unit = tree match {
    case app @ Apply(fun,args) => {
      applies = app :: applies
      super.traverse(fun)
      super.traverseTrees(args)
    }
    case _ => super.traverse(tree)
  }
}
