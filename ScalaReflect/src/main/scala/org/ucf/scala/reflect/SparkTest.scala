package org.ucf.scala.reflect
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object SparkTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkTestCase")
    val sc = new SparkContext(conf)

    val distFile = sc.textFile("data.txt")

    val data = Array(1, 2, 3, 4, 5)
    val distData = sc.parallelize(data)
    distData.map(_ + 2)
    val add2 = distData.map{
      case e => e + 2
    }
    val reg = add2.reduce(_+_)

    sc.stop()
  }
}
