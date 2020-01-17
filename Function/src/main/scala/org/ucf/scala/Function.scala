package org.ucf.scala

import scala.io.Source

object Method {
  /**
   * The most common way to define a function is as a member of some object;
   * such as a function is called a method. For example in the following codes,
   * there are two methods.
   */
  private def processLine(filename:String, width:Int, line:String) = {
    if (line.length > width) println(filename + ": " + line.trim)
  }

  def processFile(filename:String, width:Int) = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }
}

object LocalFunction{
  /**
   * Programs should be decomposed into many small functions that each do a well-defined
   * task. Each building task should be simple enough to be understood individually.
   * Such more helper function names leads to pollute the program namespace. The solution
   * is to fix out this issue, there are two ways:
   * 1. define a private method in a object
   * 2. define a local function in an object, just like local varibles, are visible only
   * in their enclosing block.
   */

  def processFile(filename:String, width:Int) = {

    /**
     *  As a local function, processLine is in scope inside processFile,
     *  so it can access the parameters and local variables of its enclosing
     *  function, but inaccessible outside.
     * @param line
     */
    def processLine(line:String) = {
      if (line.length > width) println(filename + ": " + line.trim)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }

}