package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object ScalaOperatorDemo {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf()
        conf.setAppName("scala ops")
        conf.setMaster("local")
        val context: SparkContext = new SparkContext(conf)
        context.setLogLevel("WARN")

        val lines: RDD[String] = context.textFile("./data/words")
        val l1 = lines.count()
        println(l1)
        val words: RDD[String] = lines.flatMap(_.split(" ")) // 实际上split是map操作，flat是后面自然附带的
        val tps: RDD[(String, Int)] = words.map((_, 1))
        // val l = tps.count()
        // println(l)
        //tps.foreach(println)
        val reducedRes = tps.reduceByKey(_ + _)
        // 1. sortBy: (f:(String, Int) => K): 传入tuple，按照第二位排序
        // reducedRes.sortBy(tp=>{tp._2}).foreach(println)
        // reducedRes.foreach(_.swap) 看出: 用foreach和map遍历一一操作的区别: trans算子返回RDD，action算子直接得到结果，返回值为unit类型
        val transRDD = reducedRes.map(_.swap)
        // transRDD.sortBy()
        transRDD.sortByKey(false)//.foreach(println)
        val restoredRDD = transRDD.map(_.swap)
        restoredRDD.foreach(println)
    }
}
