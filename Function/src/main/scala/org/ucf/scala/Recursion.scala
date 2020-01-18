package org.ucf.scala

object Recursion {

  /**
   * Note that the recursive call is the last thing that happens in the evaluation of
   * function body. Here boom and bang are recursive functions, but act different
   * behavior during runtime.
   *
   * [[tail recursive]]: the recursive function all itself as their last action, like
   *                     bang function. But boom is not tail recursive function since
   *                     its last action is "boom(x - 1) + 1", rather than its call itself.
   *
   *
   *
   * The scala compiler detects tail recursion and replaces it with a jump back to the
   * beginning of the function, after updating the function parameters with new values.
   * A tail-recursive function will not build a new stack frame for each call; all calls
   * will execute in a single frame. The stack trace of bang is shown:
   *
   * scala> bang(5)
   * java.lang.Exception: bang!
   * at .bang(<console>:11)
   * ... 32 elided
   *
   * If the recursive function is not tail one, the runtime will create a stack frame for
   * each call. Each iteration will consume memory and cpu resource to run it. The stack
   * trace of boom is shown:
   *
   * scala> boom(5)
   * java.lang.Exception: boom!
   * at .boom(<console>:12)
   * at .boom(<console>:12)
   * at .boom(<console>:12)
   * at .boom(<console>:12)
   * at .boom(<console>:12)
   * at .boom(<console>:12)
   * ... 32 elided
   *
   *
   * [[head recursive]]:
   */

  // boom: (x: Int)Int
  def boom(x:Int):Int = if (x == 0) throw new Exception("boom!") else boom(x-1) + 1

  // bang: (x: Int)Int
  def bang(x:Int):Int = if (x == 0) throw new Exception("bang!") else bang(x-1)

}
