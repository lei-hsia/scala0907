package chapter01

object Method {
  def main(args: Array[String]): Unit = {
    println("max: " + getMax(10, 20))
    println("max2: " + getMax2(11, 22))
  }

  def getMax(a:Int, b:Int) : Int = {
    return if (a > b) a else b
  }

  // optimized: 返回值类型可以scala自动推断，这样简洁
  def getMax2(a:Int, b:Int) = if (a > b) a else b

  /* 惰性方法: 记录方法返回值的变量声明为lazy:方法的执行就会被推迟，直到首次使用这个值，方法才会被执行:惰性方法 */
  def getSum(a:Int, b:Int) = a + b
  lazy val sum = getSum(1, 2) // sum: Int = <lazy>
  println(sum)

  
}
