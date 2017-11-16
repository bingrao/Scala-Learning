package org.ucf.scala.extractor
import scala.util.Random

/**
  * An extractor object is an object with an unapply method.
  * apply method is like a constructor which takes arguments and creates an object,
  * unapply takes an object and tries to give back the arguments.
  * This is most often used in pattern matching and partial functions.
  */
object CustomerID {
  def apply(name:String)  = s"$name---${Random.nextLong()}"

  def unapply(customerID:String): Option[String] = {
    val name = customerID.split("---").head
    if(name.nonEmpty) Some(name) else None
  }
}

class CustomerIDTest {
  val customerID = CustomerID("Nico") //Invoke apply() function to create a object with "Nico", that is, CustomerID.apply("Nico")

  customerID match {
    //When we call case CustomerID(name) => println(name)  we’re calling the unapply method.
    case CustomerID(name) => println(name)   // prints Sukyoung
    case _ => println("Could not extract a CustomerID")
  }


  //This is equivalent to val name = CustomerID.unapply(customer2ID).get. If there is no match, a scala.MatchError is thrown:
  val CustomerID(name) = customerID
  println(name) // prints Nico

  /**
  * The return type of an unapply should be chosen as follows:
    * 1. If it is just a test, return a Boolean. For instance case even()
  * 2. If it returns a single sub-value of type T, return an Option[T]
    * 3. If you want to return several sub-values T1,...,Tn, group them in an optional tuple Option[(T1,...,Tn)]
  * 4. Sometimes, the number of sub-values isn’t fixed and we would like to return a sequence. For this reason,
  *    you can also define patterns through unapplySeq which returns Option[Seq[T]] This mechanism is used for
  *    instance in pattern case List(x1, ..., xn)
  */
}
