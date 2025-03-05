package lecture

object Expression extends App {

  // Basic Arithmetic Expression
  var sum = 10 + 5
  sum += 10
  println(sum)

  // Conditional Expression(if-else)
  val a1 = 4
  val a2 = 5
  val max = if (a1 > a2) a1 else a2
  println(max)

  // Negation
  println(!(1 == 1))

  // Block Expression
  // 맨마지막을 return하는 경향이 있음.
  val blockValue = {
    val a = 10
    val b = 5
    a + b
  }
  println(blockValue)

  //Function as an Expression
  val multiply = (x: Int, y: Int) => x * y
  println(multiply(3, 4))

  //case classes and Pattern Matching
  case class Point(x: Int, y: Int)

  val point = Point(1, 2)
  val description = point match {
    case Point(1, 1) => "Origin"
    case Point(x, 0) => s"X axis at $x"
    case Point(0, y) => s"Y axis at $y"
    case _ => "Somewhere on th plane"
  }
  println(description)

  //Collection Operation
  val numbers = List(1, 2, 3, 4, 5)
  val doubled = numbers.map(_ * 2)
  println(doubled)

  println(println(123))

}
