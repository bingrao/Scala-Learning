package org.ucf.scala

/**
 * Scala leaves out break or continue statements because they do not mesh well with
 * function literals.  But scala provides many ways without break or continue to
 * program.
 */
object Break {

  /**
   * The simplest approach is to replace every continue by an if and every break by
   * a boolean variable
   */

  def main(args: Array[String]): Unit = {
    var i = 0
    var foundIt = false
    while (i < args.length && !foundIt) {
      if (!args(i).startsWith("-")) {
        if (args(i).endsWith(".scala")) {
          foundIt = true
          // code
        }
      }
      i = i + 1
    }

    // more simplify approach
    for( arg <- args
         if !arg.startsWith("-")
         if arg.endsWith(".scala")){
      // code
    }



    import scala.util.control.Breaks._
    import java.io._

    val in = new BufferedReader(new InputStreamReader(System.in))
    breakable{
      while (true){
        println("? ")
        if (in.readLine() == "") break
      }
    }





  }





}
