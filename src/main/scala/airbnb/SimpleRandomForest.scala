package airbnb

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import org.apache.spark.rdd.RDD
import scala.util.matching.Regex
import org.apache.spark

//import java.sql.Date
import java.sql.Timestamp
import org.apache.spark.sql.types
import org.apache.spark.sql._

import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.linalg.{ Vector, Vectors }

import org.apache.spark.mllib.evaluation._
import org.apache.spark.mllib.tree._
import org.apache.spark.mllib.tree.model._
import org.apache.spark.rdd._
import org.apache.spark.mllib.tree.RandomForest

object SimpleRandomForest {

  //1: Create spark session
  val spark: SparkSession =
    SparkSession
      .builder()
      .appName("Spark SQL Test")
      .config("spark.master", "local")
      .getOrCreate()

  // always import implicits so that Spark can infer types when creating Datasets
  import spark.implicits._

  implicit val e = Encoders.DATE

  // create class Users to save data
  case class User(
      id: String, date_account_created: Timestamp, timestamp_first_active: Option[Long],
      date_first_booking: Timestamp, gender: String, age: Option[Double], signup_method: String,
      signup_flow: Option[Integer], language: String, affiliate_channel: String, affiliate_provider: String,
      first_affiliate_tracked: String, signup_app: String, first_device_type: String, first_browser: String,
      country_destination: String)
     
  val sc: SparkContext = spark.sparkContext

 
  def main(args: Array[String]) {
    // print("1 + 2 + ... + 10000 = " + sum(sc.parallelize(1.to(10000))) + " :)")

    // Read file
    val rows = spark.read.option("header", true).
      option("inferSchema", true).
      csv("/Users/kimtaing/Documents/github/P_bigData/data/train_users_2.csv");
    
    // get number of columns, get distinct values of each column
    val numColumns = rows.first().toSeq.length
    val _dataValues = 0.to(numColumns-1).map(i => rows.rdd.map(_.toSeq(i)).distinct)
    val dataValues = _dataValues.map(_.collect.toList)
    var df = rows.as[User]

    // Data processing
    df.take(10).foreach(println)
    df.printSchema()
    df.describe().show

    // Data cleaning
    val filteredDf = df.filter(_.date_first_booking != null)
      .filter(e => { e.age != None && e.age.get >= 18 && e.age.get <= 100 })
      .filter(e => { e.gender != "-unknown-" && e.gender != null })
      .filter(_.first_affiliate_tracked != null)
      .filter(_.first_browser != "-unknown-")
      .filter(e => e.country_destination != null /* && !e.country_destination.equals("US")*/)
      .filter(_.signup_flow != None)

    // Data processing after cleaning
    filteredDf.describe().show

    // transform variables type => from discrete to continuous
    val doubleDf: RDD[Array[Double]] = filteredDf.drop().rdd.map(row => List[Double](
      //Data.map(row.getString(row fieldIndex "id"), dataValues((row fieldIndex "id"))),
      row.getAs[Timestamp](row fieldIndex "date_account_created").getTime.toDouble,
      row.getLong(row fieldIndex "timestamp_first_active").toDouble,
      row.getAs[Timestamp](row fieldIndex "date_first_booking").getTime.toDouble,
      Data.map(row.getString(row fieldIndex "gender"), dataValues(row fieldIndex "gender")),
      row.getAs[Double](row fieldIndex "age"),
      Data.map(row.getString(row fieldIndex "signup_method"), dataValues(row fieldIndex "signup_method")),
      row.getAs[Integer](row fieldIndex "signup_flow").toDouble,
      Data.map(row.getString(row fieldIndex "language"), dataValues(row fieldIndex "language")),
      Data.map(row.getString(row fieldIndex "affiliate_channel"), dataValues(row fieldIndex "affiliate_channel")),
      Data.map(row.getString(row fieldIndex "affiliate_provider"), dataValues(row fieldIndex "affiliate_provider")),
      Data.map(row.getString(row fieldIndex "first_affiliate_tracked"), dataValues(row fieldIndex "first_affiliate_tracked")),
      Data.map(row.getString(row fieldIndex "signup_app"), dataValues(row fieldIndex "signup_app")),
      Data.map(row.getString(row fieldIndex "first_device_type"), dataValues(row fieldIndex "first_device_type")),
      Data.map(row.getString(row fieldIndex "first_browser"), dataValues(row fieldIndex "first_browser")),
      Data.map(row.getString(row fieldIndex "country_destination"), dataValues((row fieldIndex "country_destination")))
    ).toArray)

    // Prepare data for modeling
    val features = doubleDf.map(_.slice(0,14))
    val labels = doubleDf.map(_(14))
    
    val featureVectors = features.map(Vectors.dense(_))

    val data = labels.zip(featureVectors).map { case (x, y) => LabeledPoint(x, y) }.cache()

    // split data into two sampling : training and test
    val Array(training, test) = data.randomSplit(Array(0.8,0.2))
    
    // Modeling with Decision Tree
    def getMetrics(model: RandomForestModel, data: RDD[LabeledPoint]): MulticlassMetrics = {
      val predictionsAndLabels = data.map(example => (model.predict(example.features), example.label)
      )
    new MulticlassMetrics(predictionsAndLabels) }

    val categoricalFeaturesInfo = Map[Int, Int]()
    val model = RandomForest.trainClassifier(training, 12, categoricalFeaturesInfo,
          64, "auto", "gini", 12, 128)
  
    // prediction
    val metrics = getMetrics(model, test)
    println( "precision: " + metrics.precision)
    println( "f-score: " + metrics.fMeasure)
    println( "recall: " + metrics.recall)
    println( "confusion matrix: \n" + metrics.confusionMatrix)
    println( "class labels" )
    
    dataValues.last.foreach(println)
    
  }

}
