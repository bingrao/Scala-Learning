package org.ucf.scala


/**
  * In Scala, a protected member is only accessible from subclasses of the class in which the member is defined,
  * or the companion object.
  * In Java such accesses are also possible from other classes in the same package
  */
class Protected {

  class Super{
    protected def f(): Unit = {
      //OK: value is accessible even it is declared as protected in the companion object Super
      println("f" + Super.value)
    }
  }

  class Sub extends Super{
    f() // OK, because f is declared protected in Super and Sub is a subclass of Super
  }

  class Other {
//    (new Super).f() // error: f is not accessible, because Other does not inherit from Super
                    //  In Java, it would be still permitted because Other is in the same package as Sub
  }

  object Super{
    protected val value = 3

    (new Super).f() // Ok, f is accessible because it is defined with protected in the companion class Super
  }

  object Other{
//    Super.value // error: value is not accessible, because it is declared as protected in Super
  }

}
