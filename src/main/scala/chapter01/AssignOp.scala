package chapter01

object AssignOp {
    def main(args: Array[String]): Unit = {
        val a : Int = 3
        val b = 5

        println(a & b) // 1
        println(a | b) // 7
        println(a ^ b) // 6
        println(~a) // -4: 原反补码
        println(a >> 1) // 1
        println(a << 2) // 12
    }
}
