package lecture

object CaseClass extends App {
    case class Person(name: String, age: Int)

    // Creating instances of the case class
    val alice = Person("Alice", 30)
    val alice2 = Person("Alice", 30)
    // Equals and Haso Code
    println(alice == alice2)


    //no new keyword
    val bob = Person("Bob", 25)

    //Pattern matching on case class instances
    alice match {
        case Person("Alice", 30) => println(s"Hello, Alice! Your age is $age")
        case _ => println("Unknown person")
    }

    //Using the copy method to create a modified instance
    val olderAlice = alice.copy(age = 31)
    println(olderAlice)

    // 직렬와, 역직력화에서 사용
    //    case class Point(x: int, y: int)
    //    val point = Pint(5, 10)
    //
    //    // Serialize the case class instance to a byte array
    //    val serializedObject = serialize(point)
    //
    //    // Deserialize the byte array back into an object
    //    val deserializedObject = deserialized(serializedObject)

    case object Logger {
        def log(message: String): Unit = {
            println(s"Log: $message")
        }
    }
    Logger.log("this is a log message")


}
