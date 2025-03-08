package lecture

object StringManipuation extends App {

    //String concatenation
    val firstName = "John"
    val lastName = "Doe"
    val fullName = firstName + " " + lastName
    println(fullName)

    // String Interpolation
    val age = 30
    val message = s"My age is ${age}"
    println(message)

    // Sring number를 placeholder에 넣어 사용
    val pi = 3.14159
    val formatted = f"Pi is approximately $pi%4.2f"
    println(formatted)

    // \n을 사용해도 그대로 나옴
    val rawStr = raw"This is a \n raw String"
    println(rawStr)

    //String Method
    val text = "Scala is fun!"
    val length = text.length
    println(length)

    var char = text.charAt(0)
    println(char)

    val subStr = text.substring(0, 5)
    println(subStr)

    val words = text.split(" ")
    println(words.mkString(", "))


}
