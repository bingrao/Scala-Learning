package org.ucf.scala.reflect

class TreePrinter extends  org.scalatest .FunSuite {
  import com.github.mlangc.brackets.DefaultBeautifier
  import scala.reflect.runtime.universe.{showRaw,show}
  import org.ucf.scala.reflect.TranverseTree
  /*
  test("Print ast with Toolbox"){
    val sourcePath = "ScalaReflect/src/main/resource/ScalaApp.scala"
    val rawAST = CompilerTreeWithToolbox.parseToTree(sourcePath)
    println(DefaultBeautifier.format(showRaw(rawAST)))
  }


  test("Print AST with Global Setting"){
    val sourcePath = "ScalaReflect/src/main/resource/ScalaApp.scala"
    val rawAST = CompilerTreeWithGlobal.parseToTree(sourcePath)
    println(DefaultBeautifier.format(showRaw(rawAST)))
  }
  */
  test("Print AST with Global Setting API"){
    val sourcePath = "ScalaReflect/src/main/resource/SparkTest.scala"
    val rawAST = CompilerProvider.treeFrom(sourcePath,true)
    //println(show(rawAST))
    //println("\n\n\n")
    //println(DefaultBeautifier.format(showRaw(rawAST)))
    TranverseTree.traverse(rawAST)
  }
}
