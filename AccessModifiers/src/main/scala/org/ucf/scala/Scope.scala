package org.ucf.scala


/**
  * Access modifiers in Scala can be augmented with qualifiers. A modifier
  * of the form private[X] or protected[X] means that access is private or
  * protected “up to” X, where X designates some enclosing package, class or
  * singleton object.
  *
  *
  * Qualified access modifiers give you very fine-grained control over visibility.
  *
  * This technique is quite useful in large projects that span several packages.
  * It allows you to define things that are visible in several sub-packages of your
  * project but that remain hidden from clients external to your project.
  *
  * That is, a modifier protected[X] in a class C allows access
  * to the labeled definition in all subclasses of C and also within the enclosing
  * package, class, or object X.
  *
  *
  * 1. private[this] -- narrow down the scope
  *   1) can be accessed in current class or its companion,
  *   2) not be accessible from its subclass
  *   3) not be accessible from other instance of this class
  *
  * 2. protected[this]  -- narrow down the scope
  *   1) can be accessed in current class or its companion,
  *   2) can be accessed in its subclass,
  *   3) not be accessible from other instance of this class
  *
  * 3. private  -- default scope
  *   1) can be accessed in current class or its companion,
  *   2) not be accessible from its subclass
  *
  *
  * 4. protected  -- default scope
  *   1) can be accessed in current class or its companion,
  *   2) can be accessed in its subclass,
  *
  *
  * 5. private[class]  -- expand the scope
  *   1) can be accessed in current class or its companion,
  *   2) not be accessible from other instance of this class
  *   2) can be accessed by all members contained in "class"
  *
  *
  * 6. protected[class] -- expand the scope
  *   1) can be accessed in current class or its companion,
  *   2) can be accessed by its subclass
  *   3) can be accessed by all members contained in "class"
  *
  *
  *
  * 7. private[package] -- expand the scope
  *   1) can be accessed in current class or its companion,
  *   2) not be accessible from other instance of this class
  *   3) can be accessed by all members contained in "package"
  *
  *
  * 8. protected[package] -- expand the scope
  *   1) can be accessed in current class or its companion,
  *   2) can be accessed by its subclass
  *   3) can be accessed by all members contained in "package"
  *
  *
  * 9. private[object] -- expand the scope
  *   1) can be accessed in current class or its companion,
  *   2) not be accessible from other instance of this class
  *   3) can be accessed by all members contained in "object"
  *
  *
  * 10. protected[object] -- expand the scope
  *   1) can be accessed in current class or its companion,
  *   2) can be accessed by its subclass
  *   3) can be accessed by all members contained in "object"
  */


package bobsrockets

package navigation{

  /**
    * This class is visible in all classes and objects that are contained in package "bobsrockets".
    * On the other hand, all code outside the package "bobsrockets" cannot access class Navigator
    */
  private[bobsrockets] class Navigator{

    /**
      * The useStarChart method is accessible in all subclasses of Navigator and also in all code contained
      * in the enclosing package navigation
      */
    protected[navigation] def useStarChart() {}
    class LegOfJourney{
      /**
        * "distance" is visible from everywhere in class Navigator, even it is declared as private member
        */
      private[Navigator] val distance = 100
    }

    val leg = (new LegOfJourney).distance // OK, leg is contained in class Navigator

    /**
      * The most restrictive access is to mark a method or variables as [[object-private]].
      * When you do this, the method is available only to the current instance of the current object.
      * Other instances of the same class cannot access the method
      * speed is accessible only from within the same object that contains the definition
      */
    private [this] var speed = 200
    protected [this] var size = 300

    def doNav(other:Navigator): Unit ={
//      if((other.speed > this.speed) && (other.size < this.size)){
//        // Error: because current Navigator class can not access the "speed"  and "size" of the other instance "other"
//        // Because "speed" and "size" are declared as private[this], protected[this]
//        // If "speed" is declared as private, it will pass the compilation
//      }
    }
  }



  class Gaode{
    val nav = new Navigator
    nav.useStarChart() //OK, even useStarChart is defined as protected, but it is also accessible in all package navigation
  }



  class Foo{
    /**
      * [[Package scope]]
      * To make a method available to all members of the current package, Beyond making a method available to
      * classes in the current package, Scala gives you more control and lets you make a method available at
      * different levels in a class hierarchy when you declare an package/class/object with different Package
      * Scope to allow fine-grained level of access of control, See above example
      */
    private [bobsrockets] def doX {}
    private def doY {}
  }
  class Bar {
    val f = new Foo
    f.doX  // compiles
//    f.doY  // won't compile
  }


}

package launch{
  import navigation.{Navigator, _}

  class Google extends Navigator{
    def start(): Unit ={
      /**
        * useStarChart() is accessible because the class Google is a subclass of Navigator
        */
      useStarChart()

      /**
        * the ${speed} is declared with private[this], it only can access in the class, not in a subclass
        * but ${size} is declared with protected[this], it only can access in the current instance,
        * rather than other instances, so it can be accessed
        */
//      println(s"The speed is ${speed}, the size is ${size}")
    }
  }

  class Baidu {
    val nav = new Navigator
//    nav.useStarChart() // Error: useStarChart() is declared as protected and Baidu is not in the pacakge navigation
  }
  object Vehicle {

    /**
      * In particular, the access to Navigator in object Vehicle is permitted, because Vehicle is contained
      * in package launch, which is contained in bobsrockets.
      */
    private[launch] val guide = new Navigator
  }
}
