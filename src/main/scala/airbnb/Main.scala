package airbnb

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import org.apache.spark.rdd.RDD
import scala.util.matching.Regex

object Main {
  
  val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("airbnb")
  val sc: SparkContext = new SparkContext(conf)
  
  def sum(rdd: RDD[Int]): Int = rdd.reduce(_+_)
  
  def main(args: Array[String]) {
    print("1 + 2 + ... + 10000 = " + sum(sc.parallelize(1.to(10000))) + " :)")
  }
}
