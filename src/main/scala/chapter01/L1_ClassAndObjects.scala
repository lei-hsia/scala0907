package chapter01

class L1_ClassAndObjects() {
    // 同一个文件中,class和object名称相同时, 这个class叫做object的伴生类,object叫做class的伴生对象, 相互可以访问对方的私有变量
    private val index = 5
}

//在类当中重写构造，构造中第一行需要调用默认构造
//在Scala当中，new Person时 出来方法不执行【除了构造方法】其他都执行
class Person(uname:String, uage:Int) {
    val name = uname
    val age = uage
    var gender = 'M'

    println("====== begin =======")

    def sayHello() = {
        println("say something")
    }

    //内部自定义重载constructor
    def this(name:String, age:Int, gender: Char) {
        this(name, age) // 首行必须调用class自带的constructor
        this.gender = gender
    }

    println("******** end *********")
}

// object相当于Java中的singleton，定义的全是静态的，object不能传递参数
object L1_ClassAndObjects {

    // 静态的先执行
    println("this comes first == static")

    def apply(i:Int) = {
        println("score is: "+i)
    }

    def main(args: Array[String]): Unit = {
        val person = new Person("lei", 24) // new xxx()生成对象时，除了方法不执行，其他的都执行, 所以print begin end最先打印
        println(person.name)
        println(person.age)
        println(person.gender)

        val obj = new L1_ClassAndObjects() // new 对象，使用的class L1
        println(obj.index)
        println(person.gender)

        L1_ClassAndObjects(1000) // object本来不能传参(不是class那种传参构造), 如果看到传参 那么这个object中有apply方法
        println("*******************************************") // mark the end of chap "class & object"
        // *******************************************

        for (i <- 1 to 10) print(i)
        println()
        for (i <- 1 until 10) print(i)
        println()
        val i = 1.to(10,2)
        println(i)
        val a = 1.until(10,3)
        println(a)

        // 方法1
        /*for (i <- 1 to 9){
            for (j <- 1 to 9){
                if (i>=j) {
                    print(i +"*"+j +"="+i*j +"\t")
                }
                if (i ==j) println()
            }
        }*/
        // 方法2
        for (i <- 1 to 9 ; j <- 1 to 9){
            {
                if (i>=j) {
                    print(i +"*"+j +"="+i*j +"\t")
                }
                if (i ==j) println()
            }
        }

        // 条件可以写在for循环内部; 用;或者空格隔开
        for (i <- 1 to 30 if(i > 10) if (i % 3 == 0)) print(i + "\t")

    }
}
