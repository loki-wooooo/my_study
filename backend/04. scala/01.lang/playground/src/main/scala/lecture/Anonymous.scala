package lecture

object Anonymous extends App {

    abstract class Swimmer {
        def swim: Unit
    }

    val swimmer = new Swimmer {
        override def swim: Unit = println("Swimming...")
    }
    swimmer.swim


    trait Greeter(val name: String) {
        def greet(target: String): String
    }

    // Create an instance of the Greeter trait using an anonymous class
    val greeter = new Greeter("Alice") {
        def greet(target: String): String = s"Hello, $name greets to $target!"
    }

    //use the greeter to greet someone
    val greeting = greeter.greet("Alice")
    println(greeting)

}
