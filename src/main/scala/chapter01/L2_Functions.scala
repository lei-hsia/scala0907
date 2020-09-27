package chapter01

/*
*       1. 最后返回值可以使用return，如果用了return，那么方法体的返回值类型一定不能省却
*       2. 如果没有return，那么最后一行计算的结果会作为结果返回；此时对应的返回类型可以不写，会自动推断
*       3. 传参一定要指定类型
*       4. 如果可以一行搞定，那么方法体的"{...}"可以省却
*       5.
* */
object L2_Functions {
    def main(args: Array[String]) = {

        // 1
        /*def max(a:Int, b:Int): Int = {
            if (a > b) return a
            else return b
        }*/

        // 2
        /*def max(a:Int, b:Int): Int = {
            if (a > b) a else b
        }*/
        def max(a:Int, b:Int) = if (a > b) a else b

        val res = max(100, 20)
        print(res)
        
    }
}
