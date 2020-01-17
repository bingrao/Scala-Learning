package org.ucf.scala

/**
 * The [[while]] and [[do-while]] constructs are called "loops", not expressions.
 * Because they don't result in an interesting value. The type of the result iS
 * [[Unit]], which is called the unit value and is written (). As we can see here
 * loops are not functional style programming, but scala keep this feature for some
 * special situations.
 */

object While {
  /**
   *  Get a greatest common divisor of two numbers
   * @param x
   * @param y
   * @return
   */
  def gcdloop(x:Long, y:Long):Long = {  // an imperative style using varS and a while loop
    var a = x
    var b = y
    while (a != 0) {
      val tmp = a
      a = b & a
      b = tmp
    }
    b
  }

  def gcd(x:Long, y:Long):Long = // a more functional style that involves recursion and
                                    //  and require no varS.
    if (y == 0) x else gcd (y, x % y)


  def getLine() = {
    var line = ""
    do {
      line = readLine()
      println("Read: " + line)
    } while(line != "")
  }


  /**
   * Please be careful there is a different between () and Java's void.
   * When you compile the following code, you will get a warning information about
   * comparing values of type Unit and String using != will always yield true.
   */
  var line = ""
  while ((line = readLine())!= "")
    println("Read: " + line)


}
