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

    /*    mapPartitionsWithIndex: 可以看出还是按照chunk一块一块切分的
    * */
    val rdd2 = rdd1.mapPartitionsWithIndex((index, iter)=>{
      val list = new ListBuffer[String]
      while (iter.hasNext){
        val e = iter.next()
        list.+=(s"rdd3 partition=${index}, value=${e}")
      }
      list.iterator
    })

    rdd2.foreach(println)
    println("**************")

    /*    repartition
    * */
    val rdd3 = rdd2.repartition(4) // 2
    rdd3.foreach(println)// 改变partition的分区数量之后实际上并没有改变真实的分布，因为partition数量只是逻辑上的变化
    println()

    val rdd4 = rdd3.mapPartitionsWithIndex((index, iter)=>{
      val list = new ListBuffer[String]
      while (iter.hasNext){
        val e = iter.next()
        list.+=(s"rdd4 partition=${index}, value=${e}")
      }
      list.iterator
    })

    rdd4.foreach(println) // 看出，原来的0号均匀地去往了新的0 1 2 3号各一个, 说明repartition是wide dependent的算子，产生shuffle


    /*
    *   coalesce: 可增可减分区，默认是没有shuffle的，但是可以指定；如果不让shuffle且增加partition会产生空分区，没有作用，
    *   所以repartition常用于增加分区，coalesce常用于减少分区
    *
    *   coalesce: 默认不shuffle，可以指定允许shuffle
    * */
    val rdd5 = rdd2.coalesce(2)
    val rdd6 = rdd5.mapPartitionsWithIndex((index, iter)=>{
      val list = new ListBuffer[String]
      while (iter.hasNext){
        val e = iter.next()
        list.+=(s"rdd6 partition=${index}, value=${e}")
      }
      list.iterator
    })
    rdd6.foreach(println)

  }
}
