package airbnb

import org.scalatest.{FunSuite, BeforeAndAfterAll}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import Main._

@RunWith(classOf[JUnitRunner])
class TestSuite extends FunSuite with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    sc.stop()
  }

  test("sum of 1 through 10000 sould return 50005000") {
    val res = sum(sc.parallelize(1.to(10000)))
    assert(res == 50005000, "sum of 1 and 1 sould equal 1")
  }
}

