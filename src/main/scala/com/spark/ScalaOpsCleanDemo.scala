package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object ScalaOpsCleanDemo {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf()
        conf.setAppName("scala ops")
        conf.setMaster("local")
        val context: SparkContext = new SparkContext(conf)
        context.setLogLevel("WARN")

        val lines: RDD[String] = context.textFile("./data/words")
        // transformation ops; sort demo
        lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).map(_.swap).sortByKey(false).map(_.swap).foreach(println)

        // action ops: count, take, first, collect, etc.
        /*lines.count()
        lines.first()
        val strings1 = lines.take(4)
        strings1.foreach(println)
        val strings = lines.collect()*/
        // strings.foreach(println)
    }
}
