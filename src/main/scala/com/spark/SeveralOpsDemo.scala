package com.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object SeveralOpsDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("test")

    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val nameRDD: RDD[(String, Int)] = sc.parallelize(List[(String, Int)]
      (("lei", 18), ("xia", 19), ("L", 20), ("X", 21)), 3)

    val scoreRDD: RDD[(String, Int)] = sc.parallelize(List[(String, Int)]
      (("lei", 100), ("xia", 200), ("L", 300), ("XXX", 400)), 4)

    val rdd1: RDD[(String, (Int, Int))] = nameRDD.join(scoreRDD) // 以nameRDD为基准，join匹配和nameRDD相符合的scoreRDD
    rdd1.foreach(println)
    println()

    val rdd2: RDD[(String, (Int, Option[Int]))] = nameRDD.leftOuterJoin(scoreRDD)
    rdd2.foreach(e=>{
      val name = e._1
      val v1 = e._2._1
      val v2 = e._2._2.getOrElse("10000") // getOrElse: get option's value if non-empty, else default value
      println(s"name=$name, v1=$v1, v2=$v2")
    })
    println()

    val rdd3: RDD[(String, (Option[Int], Int))] = nameRDD.rightOuterJoin(scoreRDD)
    rdd3.foreach(println)
    println()

    val rdd4: RDD[(String, (Option[Int], Option[Int]))] = nameRDD.fullOuterJoin(scoreRDD)
    rdd4.foreach(println)
    println()

    val p1 = nameRDD.getNumPartitions
    val p2 = scoreRDD.getNumPartitions
    val p3 = rdd4.getNumPartitions  // fullOuterJoin:join后的分区数和父rdd分区多的那个相同
    println(s"p1=$p1, p2=$p2, p3=$p3")
    println()

    val rdd5: RDD[(String, Int)] = nameRDD.union(scoreRDD)
    println(rdd5.getNumPartitions)  // union:只是逻辑上合并为一个RDD，实际上就是两个RDD合并起来看成一个rdd
    println()

    // intersection, subtract: 省略

    /*
    *    mapPartitions: map操作是一对一，mapPartitions是对rdd中每个partition进行一对一
    * */
    // 2个partitions，建立两次数据库连接
    val rddMP = sc.parallelize(List[String]("hello1", "hello2", "hello3", "hello4"), 2)

    val rddMPed = rddMP.mapPartitions(iter => {
      val listBuffer = new ListBuffer[String]

      println("建立数据库连接")
      while (iter.hasNext) { // 按partition处理数据
        val str = iter.next()
        println(s"插入数据库: $str")
        listBuffer.+=(str)
      }
      println("关闭数据库连接")

      listBuffer.iterator // f:Iterator -> iterator; return
    })

      rddMPed.count() // action算子触发

  }
}
