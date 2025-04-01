package lecture

object AllPatterns extends App {

    //matching on constants
    val x = 42
    val result1 = x match {
        case 0 => "Zero"
        case 1 => "One"
        case _ => "Other"
    }

    println(result1)

    //matching on types
    val obj: Any = "Hello"
    val result2 = obj match {
        case str: String => s"String: $str"
        case i: Int => s"Int: $i"
        case _ => "Other type"
    }
    println(result2)

    //matching on sequences
    val seq = Seq(1, 2, 3)
    val result3 = seq match {
        case Seq(1, 2, 3) => "Sequence matches"
        case _ => "Sequence does not match"
    }
    println(result3)

    //matching with guards
    val y = 10
    val result4 = y match {
        case even if even % 2 == 0 => "Even"
        case _ => "Odd"
    }
    println(result4)

    //matching with tuple
    val tuple = (1, "Hello")
    val result5 = tuple match {
        // case(Int, str) 도 가능
        case (1, str) => s"First element is 1 and second is $str"
        case (_, _) => "Other"
    }
    println(result5)

    case class Person(name: String, age: Int)

    val person = Person("alice", 30)
    val result6 = person match {
        case Person("Alice", _) => "Found Alice"
        case Person(_, age) if age < 18 => "Found a minor"
        case _ => "Other"
    }
    println(result6)

    val maybeValue: Option[Int] = Some(42)
    val result7 = maybeValue match {
        case Some(value) => s"Value is $value"
        case None => "No value"
    }
    println(result7)

    val pattern = "([a-zA-Z]+) ([0-9]+)".r
    val input = "Hello 123"
    val result8 = input match {
        case pattern(word, number) => s"Word: $word, Number: $number"
        case _ => "No Match"
    }
    println(result8)


    val z = 42
    val result9 = z match {
        case n if n < 0 => "Negative"
        case n if n >= 0 && n <= 10 => "Between 0 and 10"
        case _ => "Other"
    }
    println(result9)

    val nestedTuple = ((1, 2), (3, 4))
    val result10 = nestedTuple match {
        case ((a, b), (c, d)) => s"First Tuple: ($a, $b), Second Tuple: ($c, $d)"
        case _ => "No Match"
    }
    println(result10)

    /**
     * head -> list의 맨 처음
     * tail -> 처음을 제외한 나머지
     * */
    val list1 = List(1, 2, 3, 4)
    val result11 = list1 match {
        case head :: tail if head == 1 => println(s"List starts with 1 and has tail $tail")
        case _ => "No Match"
    }
    println(result11)

    val list2 = List(1, List(2, 3), 4)
    val result12 = list2 match {
        case x :: List(y, z) :: tail => println(s"Method: $x, $y, $z")
        case _ => "No Match"
    }
    println(result12)

    val list3 = List(1, 2, 3, 4, 5)
    list3 match {
        case head :: tail if tail.length >= 3 => println(s"List has at least 3 elements after $head")
        case _ => println("No Match")
    }

    val list4 = List(1, "hello", 2, "world", 3)
    list4 match {
        case a :: _ :: b :: c :: Nil => println(s"Matched $a, $b, $c")
        case _ => println("No Match")
    }

    val list5 = List(1,2,3,4,5,6)
    list5 match {
        case List(_, _, a, _, _, b) => println(s"Matched: $a, $b")
        case _ => println("No Match")
    }

}
