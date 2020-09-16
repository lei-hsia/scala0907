package chapter01

object DataType {
  // 通过print出来的东西看出来main函数总是最后执行的
  def main(args: Array[String]): Unit = {
    val f : Float = 3.1415926f // print: 丢失精度
    val d : Double = 3.1415926
    val c : Char = 'a'

    // %s: double对应; %x: 字符串对应
    printf("%f, %s, %c", f, d, c)
    println()
    // 类型: Unit, Null, Nothing; Unit类型唯一的取值:()  Null类型唯一取值:null
    val res = sayHello()
    println("res=" + res)

    println("Null: null可以给任何AnyRef类型，但是不能给AnyVal类型")
    val dog : Dog = null
    // val char : Char = null  // Error:(18, 23) an expression of type Null is ineligible for implicit conversion

  }

  def sayHello() : Unit = {

  }

  println(10 max(2))

  println()

  // 强制类型转换
  val num1 : Int = 10 * 1.5.toInt + 3.5.toInt
  val num2 : Int = (10 * 1.5 + 3.5).toInt
  println(num1 + " " + num2)

  // 赋值: 右边没有运算操作就会自动匹配类型; 有运算操作就会变为高精度再赋值
  var s : Short = 5
  // s = s -2

  println(12.5.toInt)
  // scala中不是把小数点后面的数字进行截取，而是直接throw exception
  val str1:String = "13.3"
  // println(str1.toInt)  // error
  println(str1.toDouble)  // okay

  // 首字符为操作符( + - * / )，那么后面也至少跟一个操作符
  val ++ = "hello"
  println(++)

  val Int = 33
  println(Int)
}

class Dog { }