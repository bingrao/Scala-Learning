package org.ucf.scala

import java.io.{File, PrintWriter}

/**
 * In languages with first-class functions, you can effectively make new control structures
 * even though the syntax of the language is fixed. All you need to do is create methods
 * that take functions as arguments.
 */
object ControlAbstraction {
  /**
   * [[load pattern]] is a more widely used coding pattern: open a source, operate on it,
   * and then close the resource.
   *
   * In the following example, it loans a PrintWriter to the function, op. When the function
   * op completes, it signals that it no longer needs the "borrowed" resource, that's writer.
   * The borrowed resource is then closed in a finally block, to ensure it is indeed closed,
   * regardless of whether the function completes by returning normally or throwing an
   * exception.
   *
   * @param file
   * @param op
   */
  def withPrinterWriter(file:File, op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  withPrinterWriter(new File("date.txt"),
    writer => writer.println(new java.util.Date()))


  /**
   *
   */

  def withPrinterWriter_(file:File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  withPrinterWriter_(new File("date.txt")){
    writer => writer.println(new java.util.Date())
  }

}
