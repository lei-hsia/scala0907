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
    rdd4 partition=0, value=rdd3 partition=0, value=lei2
    rdd4 partition=0, value=rdd3 partition=1, value=lei8
    rdd4 partition=0, value=rdd3 partition=2, value=lei9
    rdd4 partition=1, value=rdd3 partition=0, value=lei3
    rdd4 partition=1, value=rdd3 partition=1, value=lei5
    rdd4 partition=1, value=rdd3 partition=2, value=lei10
    rdd4 partition=2, value=rdd3 partition=0, value=lei4
    rdd4 partition=2, value=rdd3 partition=1, value=lei6
    rdd4 partition=2, value=rdd3 partition=2, value=lei11
    rdd4 partition=3, value=rdd3 partition=0, value=lei1
    rdd4 partition=3, value=rdd3 partition=1, value=lei7
    rdd4 partition=3, value=rdd3 partition=2, value=lei12
    * */

  }
}
