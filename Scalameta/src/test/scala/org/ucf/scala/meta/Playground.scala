package org.ucf.scala.meta
import org.scalameta.logger // useful for debugging

/**
  * Created by Bing on 10/12/2017.
  */
class Playground extends org.scalatest.FunSuite {
  import scala.meta._
  test("Part1: Tokens"){
    val tokens = "val x = 2".tokenize.get
    logger.elem(tokens.syntax)
    logger.elem(tokens.structure)
    logger.elem(tokens.map(x => f"${x.structure}%10s -> ${x.productPrefix}").mkString("\n"))

  }
  test("Part2: trees"){
    val tree = "val x = 2".parse[Stat].get
    logger.elem(tree.syntax)
    logger.elem(tree.structure)
  }
  test("Part3: source file"){
    val srcPath = "scalameta/src/main/scala/org/ucf/scala/meta/ScalaApp.scala"
    val srcFile = new java.io.File(srcPath)
    val source = srcFile.parse[Source].get
    logger.elem(source.syntax)
    logger.elem(source.structure)
  }
}
