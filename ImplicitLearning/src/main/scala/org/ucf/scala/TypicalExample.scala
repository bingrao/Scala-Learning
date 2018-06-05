package org.ucf.scala

import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.JButton

class TypicalExample {
  val button = new JButton()

  // Java-Style add an ActionListener
  button.addActionListener(
    new ActionListener { // inner class
      override def actionPerformed(e: ActionEvent): Unit = println("Pressed!")
    }
  )

  // Scala-Style add an ActionListener

  // Here is an implicit conversion from functions to action listeners
  implicit def function2ActionListener(f:ActionEvent => Unit) =
    new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = f(e)
    }

  /**
    * The way this code work is that the compiler first tries to compile it as is, but it sees a type error.
    * Before giving up, it looks for an implicit conversion that can repair the problem. Here it finds
    * "function2ActionListener" to convert a function to an action Listener, then moves on.
    */
  button.addActionListener(
    (_:ActionEvent) => println("Pressed!")
  )
}
