package org.ucf.scala

import java.io.File

object For {

  /**
   * Iteration through any kind of collections, such as array, list, range
   * as shown in following case:
   * In each iteration, a new val named file is initialized with an element value in
   * [[fileHere]], and then the body will be executed.
   *
   */
  val fileHere = (new File(".")).listFiles() // Array[File]
  for (file <- fileHere)  // generator syntax, [[fileHere]] is an array of Files.
    println(file)


  for (i <- 1 to 4) // a range [1, 2, 3, 4], which includes the upper bound.
      println("Iteration " + i)

  for (i <- 1 until 4) // a range [1, 2, 3], which do not includes the upper bound.
    println("Iteration " + i)

  /**
   * Get an index of an array
   */
  for (index <- fileHere.indices)  //Not common to use [[for (index <- 0 until fileHere.size)]]
    println("Index " + index)


  /**
   * It is a very common usage in other languages to iterative all elements in a collection.
   * But Scala does not take this way Since the following two reasons:
   * 1. you can use generator syntax to access all element easily.
   * 2. it is hard to determine the start value of the index, 0 or 1, or -1.
   */
  for (index <- 0 until fileHere.size)
    println(fileHere(index))


  /**
   * Iterate a collection with a condition, which means you want to filter it down to
   * some subset.
   */

  for (file <- fileHere
       if file.getName.endsWith(".scala") // Be difference with if expression condition
       )
    println(file)
  // You can alternatively accomplish the same goal with this imperative form code:
  for (file <- fileHere)
    if (file.getName.endsWith(".scala")) println(file)

  // You can include more filters if you want,
  // and the filter statements are the relationship of &&
  for (file <- fileHere
       if file.isFile
       if file.getName.endsWith(".scala"))
    println(file)


  /**
   * Nested Iteration
   */

  def fileLines(file:java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def grep(pattern:String) =
    for (
      file <- fileHere if file.getName.endsWith(".scala"); // Outer loop iterates over fileHere
      line <- fileLines(file) if line.trim.matches(pattern) // inter loop over fileLines(file)
    ) println(file + ": " + line.trim)

  // the following code accomplishes the same goal with grep.
  def grep_1(pattern:String) =
    for ( file <- fileHere if file.getName.endsWith(".scala"))
      for (line <- fileLines(file) if line.trim.matches(pattern))
        println(file + ": " + line.trim)

  // the following code accomplishes the same goal with grep.
  // Using curly brace "{}" instead of parentheses "()" to surround
  // the generators and filters in grep for expression, so that you
  // can leave off some of the semicolons.

  def grep_2(pattern:String) =
    for {
      file <- fileHere if file.getName.endsWith(".scala") // Outer loop iterates over fileHere
      line <- fileLines(file) if line.trim.matches(pattern) // inter loop over fileLines(file)
    } println(file + ": " + line.trim)

  /**
   *  Mid-stream variable bindings
   * @param pattern
   */
  def grep_3(pattern:String) =
    for {
      file <- fileHere if file.getName.endsWith(".scala") // Outer loop iterates over fileHere
      line <- fileLines(file) // inter loop over fileLines(file)
      trimmed = line.trim // bonding the result to a new variable trimmed to aviod redundant
                          // computation, like a val declare, but val keyword left out.
      if line.trim.matches(pattern) // the filter in the inner loop
    } println(file + ": " + trimmed)


  /**
   * Producing a new collection
   * syntax:  for clauses yield body
   * yield keyword goes before the entire body.
   */

  def scalaFiles = // generate an array of files.
    for { // the condition of for expression
      file <- fileHere if file.getName.endsWith(".scala")
    } yield { // the body of for expression. Each time the body executes,
      // and it produces one value, in this case simply file. When the for
      // expression is done, the result will include all of yielded values
      // contained in a single collection. The type of resulting collection
      // is based on the kind of collections processed in the iterations.
      file
    }

}
