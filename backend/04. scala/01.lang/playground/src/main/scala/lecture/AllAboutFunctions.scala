package lecture

object AllAboutFunctions extends App {

    // basic syntax
    def add(a: Int, b: Int): Int = {
        a + b
    }

    println(add(2, 3))

    // anonymous function
    var multiply = (x: Int, y: Int) => x * y
    println(multiply(2, 3))

    // higher-order function
    def applyOperation(x: Int, y: Int, operation: (Int, Int) => Int): Int = {
        operation(x, y)
    }

    println(applyOperation(4, 3, (a, b) => a + b))

    // partially applied function
    val addThree = add(3, _: Int)
    println(addThree(4))

    // curried function
    def multiply2(x: Int)(y: Int): Int = x * y

    println(multiply2(3)(4))
    val multiplyTen = multiply2(10) _
    println(multiplyTen(3))

    // FunctionN traits where N is number of parameters - Function Type
    // supports Function ~ Function22
    // e.g Function2[Int, Int, Int] meas, (Int, Int) as Input and the last Int is Output
    // int - InputValue , String - Output Value
    val convert2String: Function1[Int, String] = _.toString
    println(convert2String(100))

    // FucntionN has N + 1 type parameters
    var lengthFunction = new Function1[String, Int] {
        //객체를 함수처럼 호출할 수 있게 해주는 역할
        def apply(string: String): Int = string.length
    }
    /*
    * 위와 동일함
     trait Function1[A, B] {
        def apply(item: A): B
    }
    * */
    println(lengthFunction("HELLO"))

    var subtractor = new((Int, Int) => Int) {
        def apply(n1: Int, n2: Int): Int = n1 - n2
    }
    println(subtractor(10, 2))

    val concatenate: Function3[String, String, String, String] = _ + _ + _
    println(concatenate("Hello", "Scala", "World"))

    //Tuples as arguments
    val sumTuple: ((Int, Int)) => Int = tuple => tuple._1 + tuple._2
    println(sumTuple(10, 10))
}
