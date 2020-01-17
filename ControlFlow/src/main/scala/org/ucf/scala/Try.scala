package org.ucf.scala

object Try {

  import java.io.FileReader
  import java.io.FileNotFoundException
  import java.io.IOException

  /**
   * try-catch-finally {try-catch, try-finally}
   */
  val f = new FileReader("input.txt")
  try { // The body is executed and if it throw an exception, then this exception
       // would be catch by catch clause.

    // the code to use and close file

  } catch {  // The exception will test following cases one by one from top to bottom.
    // If one is matched, the corresponding code will be executed. If there is no match
    // case, the try-catch will terminate and the exception will propagate further.
    case ex: FileNotFoundException => // Handle missing file
    case ex: IOException => // Handle other I/O error
  } finally {
    // Code here would be executed no matter how the body expression of try terminates.
    f.close()  // Be sure to close the file
  }


  /**
   * try-catch-finally yields a value
   */

  import java.net.URL
  import java.net.MalformedURLException

  def urlFor(path:String) =
    try {
      new URL(path)
    } catch {
      case e:MalformedURLException => new URL("...")
    } finally {
      // If there is a finally clause, the return value here is dropped.
      // Usually finally clauses do some kind of clean up, such as
      // closing a file. Hence, They should not change the value computed
      // in the main body or the body of try-catch clause.
    }

  // But if a finally clause includes an explicit return statement, or
  // throws an exception, that return value or exception will overrule
  // any previous one that originated in the try block or one of its
  // catch clauses.

  def f_():Int = try return 1 finally return 2 // call f results in 2
  def g():Int = try return 1 finally 2 // call f results in 1

}
