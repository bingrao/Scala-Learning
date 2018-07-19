package org.ucf.scala

/**
  * A member labeled private is visible only inside the class or companion object that contains the member definition.
  * but it is not available to subclasses.
  */

class Private{
  class Inner{
    /**
      * Even the value in object Inner is label as private, but it can be accessed by
      * the companion class
      */
    private def f() { println("f" + Inner.value) }
    class InnerMost{
      f() // OK
    }

    def doFoo(other:Inner): Unit = {
      other.f() // Ok
      (new Inner).f() // OK
    }
  }

  /**
    * f is declared private in Inner and the access is not from within class Inner
    */

//  (new Inner).f() // error: f is not accessible


  object Inner{
    private val value = 0
    def g():Unit = {
      /**
        * because at this time object Inner is a companion object to class Inner,
        * all private member in class of Inner are accessible in its companion object
        */
      (new Inner).f() // OK,
    }
  }

  class Outer{
    def doFooOuter(other:Inner): Unit = {
//      other.f() // Error, compared with above case doFoo
    }
  }

  object Outer{
//    val reg = Inner.value //error: value is not accessible because it is private member in object Inner
  }
}
