package org.ucf.scala




/**
  * String Interpolation allows users to embed variable references directly in processed string literals
  * Scala provides three string interpolation methods out of the box: s, f and raw
  * ref: https://docs.scala-lang.org/overviews/core/string-interpolation.html#advanced-usage
  */

object StringInterpolation {

  /**
    * Prepend s to any string literal allows the usage of variables directly in the string.
    */
  private  val name = "Bing"
  def printInfo_s:Unit = println(s"my name is: \n$name")

  /**
    * Prepend f to any string literal allows the creation of simple formatted strings,
    * similar to "printf" in other languages.
    */
  val height = 1.9d
  def printInfo_f:Unit = println(f"$name%s is $height%2.2f meters tall")

  /**
    * The raw interpolator is similar to the s interpolator except that it performs
    * no escaping of literals within the string.
    */
  def printInfo_raw:Unit = println(raw"my name is: \n$name")
}

/**
  * In addition to the three default string interpolators, users can define their own.
  */
/*
import scala.util.parsing.json.JSONObject
implicit class JsonHelper(val sc:StringContext) extends AnyVal {
  def json(args:Any*):JSONObject = {
    val strings = sc.parts.iterator
    val expressions = args.iterator
    val buf = new StringBuffer(strings.next())
    while(strings.hasNext){
      buf append expressions.next
      buf append strings.next
    }
    parseJson(buf)
  }
}
*/


