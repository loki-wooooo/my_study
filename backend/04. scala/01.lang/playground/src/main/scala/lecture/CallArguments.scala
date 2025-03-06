package lecture

object CallArguments extends App{

    def callByValue(x: Long): Unit = {
        println(s"Value : ${x}")
        println(s"Value : ${x}")
    }

    def callByName(x: =>  Long  ): Unit = {
        println(s"Value : ${x}") //evaluate
        println(s"Value : ${x}") //evaluate again
    }

    callByValue(System.nanoTime())
    callByName(System.nanoTime())

}
