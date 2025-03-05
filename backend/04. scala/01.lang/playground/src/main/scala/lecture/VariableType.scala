package lecture

/**
 * def main(args: Array[String]): Unit = { } -> extends App 대체 가능
 *
 * */
object VariableType extends App {
  //Immutable
  val constantValue: Int = 30;
  println(constantValue);

  //Mutable
  var mutableValue = 10;
  mutableValue = 20;
  println(mutableValue);

  // Type inference
  val number = 32;
  println(number.isInstanceOf[Int])
  val name = "Scala"
  println(name.isInstanceOf[String])

  
}
