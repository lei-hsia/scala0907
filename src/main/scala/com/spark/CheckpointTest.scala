package com.spark

import org.apache.spark.{SparkConf, SparkContext}

object CheckpointTest {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
        conf.setAppName("checkpoint").setMaster("local")

        val context = new SparkContext(conf)
        context.setCheckpointDir("./data/ck")

        val rdd = context.textFile("./data/words")
        rdd.checkpoint()

        rdd.count()
    }
}
