package lecture

object HigherOrderFunction extends App {

    // Function as a Parameter
    def applyTwice(f: Int => Int, x: Int): Int = f(f(x))

    val double = (x: Int) => x * 2
    val doubleResult = applyTwice(double, 2)
    println(doubleResult)

    // Function Returning a Function
    def multiplier(factor: Int): Int => Int = {
        (x: Int) => x * factor
    }

    val triple = multiplier(3)
    println(triple(5))

    // Order examples
    def executeIfTrue(condition: Boolean, f: () => Unit): Unit = {
        if (condition) {
            f()
        }
    }

    executeIfTrue(5 > 3, () => println("5 is greater than 3"))

    def combine[A, B, C](f: A => B, g: B => C): A => C = {
        a => g(f(a))
    }

    val timesTwo: Int => Int = _ * 2
    val addThree: Int => Int = _ + 3
    val combined = combine(timesTwo, addThree)
    println(combined(3))

    def times(n: Int, f: () => Unit): Unit = {
        if (n > 0) {
            f()
            times(n - 1, f)
        }
    }

    times(3, () => println("Hello"))


}
