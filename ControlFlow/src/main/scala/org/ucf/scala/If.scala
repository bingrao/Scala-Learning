package org.ucf.scala

import java.io.File

object If {

  def main(args: Array[String]): Unit = {
    /**
     * In Scala If statement is an expression that results in a value.
     */
    val filename =  // String
      if (!args.isEmpty) args(0) else "default.txt"


    val filename_1 = // Any, since if statement misses the else branch
      if (!args.isEmpty) args(0)

    val filename_2 = // String
      if (!args.isEmpty) args(0)
      else if (args.size == 2) args(1)
      else "default.txt"

    /**
     * There is no ternary operator in scala, but you can use if statement instead.
     * Syntax - condition ? valueIfTrue : valueIfFalse)
     */


    /**
     * A filter statement in a for expression
     */
    val fileHere = (new File(".")).listFiles() // Array[File]
    for (file <- fileHere
         if file.isFile
         if file.getName.endsWith(".scala"))
      println(file)
  }

  /**
   * A filter statement in a match expression
   */



}
