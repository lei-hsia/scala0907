package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SeveralOpsDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("test")

    val sc = new SparkContext(conf)

    val nameRDD: RDD[(String, Int)] = sc.parallelize(List[(String, Int)]
      (("lei", 18), ("xia", 19), ("L", 20), ("X", 21)))

    val scoreRDD: RDD[(String, Int)] = sc.parallelize(List[(String, Int)]
      (("lei", 100), ("xia", 200), ("L", 300), ("XXX", 400)))

    val res: RDD[(String, (Int, Int))] = nameRDD.join(scoreRDD) // 以nameRDD为基准，join匹配和nameRDD相符合的scoreRDD

    res.foreach(println)
  }
}
