package org.ucf.scala

/**
  * Every member not labeled private or protected is public. There is no
  * explicit modifier for public members. Such members can be accessed from
  * anywhere
  */
package model {
  class Foo{
    def doX: Unit ={}
  }
}

package org.bar{
  import model._
  class Bar{
    val f = new Foo
    f.doX
  }
}
