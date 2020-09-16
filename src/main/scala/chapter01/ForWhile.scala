package chapter01
import scala.util.control.Breaks._

object ForWhile {
    def main(args: Array[String]): Unit = {
      // for循环
      for (i <- 1 to 10 if i % 3 == 0) print(i + " ")
      println()

      // scala中的for循环有返回值，使用yield可以构建出一个集合: 称为推导式
      val v = for (i <- 1 to 5) yield i * 3
      println(v)

      // while
      var i = 1
      while (i <= 10){
        print(i + " ")
        i += 1
      }
      println()

      // break: breakable将整个for循环包裹起来
      // continue: breakable将for表达式的循环体包裹起来
      // 原因: break()跳出到breakable外面: break直接完结; continue完结这次，for循环从6开始
      breakable{
        for (i <- 1 to 10) {
          if (i == 5) break();
          else print(i+" ")
        }
      }
      println()


      for (i <- 1 to 10) {
        breakable{
          if (i == 5) break();
          else print(i+" ")
        }
      }

    }
}
