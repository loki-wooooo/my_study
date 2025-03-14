package lecture

import scala.util.{Failure, Success, Try}

object Exception extends App {

    // throw keyword
    // throw new IllegalArgumentException("Invalid argument")

    def divide(x: Int, y: Int): Int = {
        if (y == 0) {
            throw new ArithmeticException("cannot divide by zero.")
        } else {
            x / y
        }
    }

    // try & catch & finally
    try {
        divide(10, 0)
    } catch {
        case e: ArithmeticException => println("Arithmetic problem: " + e.toString)
        case e: Exception => println("ERROR :: " + e.getMessage)
    } finally {
        println("This will always be printed.")
    }

    // using try class
    val result = Try(divide(10, 0))
    result match {
        case Success(value) => println(s"Result: $value")
        case Failure(exception) => println(s"Error: ${exception.getMessage}")
    }

    //custom exception
    class MyCustomException(message: String) extends Exception(message)

    def myFunction(condition: Boolean): Unit = {
        if (condition) {
            throw new MyCustomException("This is a custom exception")
        }
    }

    try {
        myFunction(true)
    } catch
        case e: Exception => println("error: " + e.getMessage)

}
