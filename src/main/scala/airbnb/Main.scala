package airbnb

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import org.apache.spark.rdd.RDD
import scala.util.matching.Regex
import org.apache.spark

import java.util.Date
import org.apache.spark.sql.types
import org.apache.spark.sql._
object Main {
  
  //1: Create spark session
  val spark: SparkSession =
    SparkSession
      .builder()
      .appName("Spark SQL Test")
      .config("spark.master", "local")
      .getOrCreate()
      
  // always import implicits so that Spark can infer types when creating Datasets
  import spark.implicits._

  /*class User(id:String, date_account_created:Date, timestamp_first_active: String
      , date_first_booking: String, gender: Double, age:Double
      , signup_method:Double, signup_flow:String, language:String
      , affiliate_channel:Double,affiliate_provider:Double,first_affiliate_tracked:String,signup_app:String
      ,first_device_type:String,first_browser:String, country_destination:String)
*/
  												
  val conf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("airbnb")
  val sc: SparkContext = new SparkContext(conf)
  
  def sum(rdd: RDD[Int]): Int = rdd.reduce(_+_)
  
  def main(args: Array[String]) {
    //print("1 + 2 + ... + 10000 = " + sum(sc.parallelize(1.to(10000))) + " :)")
    
    val df = spark.read.option("header", true).
      option("inferSchema", true).
      csv("/Users/kimtaing/Documents/github/P_bigData/data/train_users_2.csv")
  }
}
