package chapter01

import scala.actors.Actor

class MyActor extends Actor{
  override def act(): Unit = {
    receive{
      case s:String=>{println(s"type is String, value is $s")}
      case _=>{println("no match...")}
    }
  }
}

/*
*     给Actor发送消息
* */
object L_Actor1 {
  def main(args: Array[String]): Unit = {
    val actor = new MyActor
    actor.start() // thread.start() 之后才能给actor发送消息

    actor ! "hello world"
    //actor ! 100
  }
}
