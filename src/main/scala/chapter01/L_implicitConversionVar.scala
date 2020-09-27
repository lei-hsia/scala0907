package chapter01

object L_implicitConversionVar {

  // 部分参数需要implicit，只能柯里化
  def sayName(age:Int)(implicit name:String) = {
    println(s"name is $name, age is $age")
  }

  def main(args: Array[String]): Unit = {
    implicit val name:String = "lei"
    sayName(25)
  }
}
