package org.ucf.scala.reflect


import scala.tools.nsc._
import io._

import scala.tools.reflect.ToolBox
import com.google.common.io.Files



import org.ucf.scala.reflect.CompilerTreeWithGlobal.showRaw

import scala.reflect.internal.util.BatchSourceFile

object CompilerTreeWithGlobal extends Global(new Settings()){
  this.settings.usejavacp.value = true
  new Run
  def parseToTree(path:String) = {
    val code  = AbstractFile.getFile(path)
    val bfs = new BatchSourceFile(code,code.toCharArray)
    val parser = new syntaxAnalyzer.UnitParser(new CompilationUnit(bfs))
    parser.smartParse()
  }
  def parseToString(path:String) = {
    showRaw(this.parseToTree(path))
  }
}

object CompilerTreeWithToolbox {
  import scala.reflect.runtime.{universe => ru}
  import java.io.File
  import java.nio.charset.Charset
  def parseToTree(path:String) = {
    val source = Files.toString(new File(path),Charset.forName("UTF-8"))
    val tb = ru.runtimeMirror(getClass.getClassLoader).mkToolBox()
    tb.parse(source)
  }
  def parseToString(path:String) = {
    showRaw(this.parseToTree(path))
  }

  def parseToTreeTypeCheck(path:String) = {
    val source = Files.toString(new File(path),Charset.forName("UTF-8"))
    val tb = ru.runtimeMirror(getClass.getClassLoader).mkToolBox()
    tb.typecheck(tb.parse(source))
  }
}