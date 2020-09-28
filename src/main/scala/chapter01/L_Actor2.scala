package chapter01

import scala.actors.Actor

case class Msg(actor: Actor, message:String)  // 样例类 == lombok

class MyActor1 extends Actor{
  override def act(): Unit = {
    while (true) {
      receive{
        case msg:Msg=> {
          if ("hello~".equals(msg.message)){
            println(msg.message)
            msg.actor ! "hi~"
          }
          else if ("Could we have a date?".equals(msg.message)) {
            println(msg.message)
            msg.actor ! "no."
          }
        }
        case _=>{println("no match...")}
      }
    }
  }
}


class MyActor2(actor:Actor) extends Actor{

  //actor ! "hello~"
  actor ! Msg(this, "hello~")

  override def act(): Unit = {
    while (true) {
      receive{
        case s:String=>{
          if ("hi~".equals(s)){
            println(s)
            actor ! Msg(this, "Could we have a date?")
          }
          else if ("no.".equals(s)){
            println(s)
          }
        }
        case _=>{println("no match...")}
      }
    }
  }
}

/*
*   1. 先创建的actor1，然后actor2；actor2发送Msg对象给actor1，actor1发送String对象给actor2
*   2. while(true)包裹receive才能多次进行通信
* */
object L_Actor2 {
  def main(args: Array[String]): Unit = {
    val actor1 = new MyActor1
    val actor2 = new MyActor2(actor1)  // new class时候让actor2直接给actor1发送一个消息: new的时候除了方法其他的地方都执行
    actor1.start()
    actor2.start()


  }
}
