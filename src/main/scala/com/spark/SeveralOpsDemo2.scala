package com.spark

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object SeveralOpsDemo2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("demo")
    conf.setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val rdd1 = sc.parallelize(List[String](
      "lei1", "lei2", "lei3", "lei4",
      "lei5", "lei6", "lei7", "lei8",
      "lei9", "lei10", "lei11", "lei12"
    ), 3)

    /*    mapPartitionsWithIndex
    * */
    val rdd2 = rdd1.mapPartitionsWithIndex((index, iter)=>{
      val list = new ListBuffer[String]
      while (iter.hasNext){
        val e = iter.next()
        list.+=(s"rdd2 partition=${index}, value=${e}")
      }
      list.iterator
    })

    rdd2.foreach(println)
  }
}
