package lecture

object Options extends App {

  //create some and none instance
  val someValue: Option[String] = Some("Hello")
  val noneValue: Option[String] = None;

  println(s"Some Value: $someValue")
  println(s"None Value: $noneValue")

  // pattern matching on Option
  val number:Option[Int] = Some(10)

  val message = number match{
    case Some(value) => s"Result is $value"
    case None => "No result"
  }
  println(message)

  // Accessing value inside Option using getOrElse
  val somResult: Option[Int] = Some(10)
  val value = somResult.getOrElse(0)
  println(s"Value: $value")

  //Mapping over option
  val mapResult:Option[Int] = Some(10)
  val doubleResult = mapResult.map(_ * 2)
  println(s"Doubled result: $doubleResult")

  //chaining options with flatmap
  val chainResult: Option[Int] = Some(10)
  val incrementResult = chainResult.flatMap(value => Some(value +1))
  println(s"Incremented result: $incrementResult")

  // for comprehension
  val maybeValue1: Option[Int] = Some(10)
  val maybeValue2: Option[Int] = Some(20)
  val result = for {
    x <- maybeValue1
    y <- maybeValue2
  } yield  x + y
  // yield 는 Scala에서 for-comprehension 내에서 사용되며, 반복(iteration) 결과를 수집하여 새로운 컬렉션을 생성하는 데 사용
  println(result)

}
