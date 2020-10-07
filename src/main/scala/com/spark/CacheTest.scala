package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object CacheTest {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
        conf.setMaster("local").setAppName("cachetest")

        val context: SparkContext = new SparkContext(conf)

        val lines: RDD[String] = context.textFile("./data/words")

        lines.cache()
        val startTime1: Long = System.currentTimeMillis()
        val l1: Long = lines.count() // 第一次count: 懒执行所以会往上找到lines读取文件进行一次IO，经过磁盘
        val endTime1 = System.currentTimeMillis()

        println(s"${endTime1 - startTime1}ms")

        val startTime2: Long = System.currentTimeMillis()
        val l2: Long = lines.count() // 第二次count: 因为前面cache了，所以第二次count就是直接从cache读的
        val endTime2 = System.currentTimeMillis()

        println(s"${endTime2 - startTime2}ms")
    }
}
