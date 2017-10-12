package org.ucf.scala.stringinterpolation
/**
  * @author
  */
import org.junit.Test
import org.junit.Assert._

class ScalaTestAPP {
  @Test def testAdd() {
    println("Hello World From Scala");
    assertTrue(true);
  }
  @Test def testStringInterpolation(): Unit = {
    StringInterpolation.printInfo_s
    StringInterpolation.printInfo_f
    StringInterpolation.printInfo_raw
  }
}