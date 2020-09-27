package chapter01

class Animal(name:String){
  def canFly() = {
    println(s"$name can fly...")
  }
}

class Rabbit(xname:String) {
  val name = xname
}

object L_implicitConversionFun {
  def main(args: Array[String]): Unit = {

    // 隐式函数: implicit def XXX(yyy):zzz = {}
    implicit def rToA(r:Rabbit):Animal = {
      new Animal(r.name) // ⚠️:Animal constructor
    }

    val rabbit = new Rabbit("aRabbit")
    rabbit.canFly()
  }
}
