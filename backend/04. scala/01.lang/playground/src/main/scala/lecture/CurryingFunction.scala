package lecture

object CurryingFunction extends App {
    def adding(a: Int)(b: Int): Int = a + b

    val addingTwo = adding(2)
    println(addingTwo(3))

    def discount(discountPercentage: Double)(price: Double): Double = price * (1 - discountPercentage/100)
    
    //create parital function
    val tenPercentOff = discount(10) _
    val twentyPercentOff = discount(20) _

    println(tenPercentOff(100)) // output: 90.0
    println(twentyPercentOff(100)) // output: 80.0

    // Customizable Text Formatter
    def formatter(prefix: String)(text: String)(suffix: String): String = prefix + text + suffix

    val shout = formatter("Hey! ")(_: String)("!!!")
    val whisper = formatter("...")(_: String)("...")

    println(shout("Scala is awesome"))
    println(shout("Scala is versatile"))

    // Data Filltering
    def filter[T](condition: T => Boolean)(data: List[T]): List[T] = data.filter(condition)

    val isEven = filter[Int](_ % 2 == 0) _
    val evenNumbers = isEven(List(1, 2, 3, 4, 5, 6))
    println(evenNumbers)

    val startWithS = filter[String](s => s.startsWith("S")) _
    val words = startWithS(List("Scala", "Java", "Haskell", "Scheme"))

    // Logging with Different Levels
    def log(level: String)(message: String): Unit = {
        println(s"[$level]: $message")
    }

    val infoLog = log("INFO") _
    val errorLog = log("ERROR") _

    infoLog("System is starting up")
    errorLog("Failed to load configuration")

    // Arithmetic Operation
    def operation(op: String)(x: Int)(y: Int): Int = op match {
        case "add" => x + y
        case "subtract" => x - y
        case "multiply" => x * y
        case "divide" => x / y
    }

    val add = operation("add") _
    val subtract = operation("subtract") _
    val multiply = operation("multiply") _
    val divide = operation("divide") _

    println(add(10)(5))
    println(subtract(10)(5))
    println(multiply(10)(5))
    println(divide(10)(5))


}
