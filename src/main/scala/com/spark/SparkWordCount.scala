package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object SparkWordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("wordcount")
    conf.setMaster("local") // 运行模式
    conf.set("spark.driver.memory", "1"); // 运行资源
    // SparkContext是通往spark集群的唯一通道
    val sc = new SparkContext(conf)
    /*
    val lines: RDD[String] = sc.textFile("./data/words")
    val words: RDD[String] = lines.flatMap(line => {
      line.split(" ")
    })
    val pairWords: RDD[(String, Int)] = words.map(word => {
      new Tuple2(word, 1)
    })
    val result: RDD[(String, Int)] = pairWords.reduceByKey((v1: Int, v2: Int) => {
      v1 + v2
    })
    result.foreach(one=>println(one))*/

    sc.textFile("./data/words").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(println)
  }
}
