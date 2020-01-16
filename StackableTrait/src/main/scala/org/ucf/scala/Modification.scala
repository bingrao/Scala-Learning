package org.ucf.scala

trait Doubling extends IntQueue {

  abstract override def put(x: Int): Unit = super.put(2 * x)
}

trait Incrementing extends IntQueue{
  abstract override def put(x: Int): Unit = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int): Unit = if (x >= 0) super.put(x)
}

class MyDoublingQueue extends BasicIntQueue with Doubling
class MyIncrementingQueue extends BasicIntQueue with Incrementing
class MyFilteringQueue extends BasicIntQueue with Filtering
