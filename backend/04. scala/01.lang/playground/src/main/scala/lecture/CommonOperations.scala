package lecture

object CommonOperations extends App {
    // map
    val numbers = List(1, 2, 3, 4)
    val squares = numbers.map(x => x * x)
    println(squares)

    // flatMap
    val lists = List(List(1, 2), List(3, 4))
    val flattened = lists.flatMap(x => x.map(_ * 2)) // List(2,4,6,8)
    println(flattened)

    // filter
    val evenNumbers = numbers.filter(_ % 2 == 0)
    println(evenNumbers)

    //foreach
    numbers.foreach(println)

    // fold
    val sum = numbers.fold(0)(_ + _)
    println(sum)

    // reduce
    val product = numbers.reduce(_ * _)
    println(product)

    // group by
    val groupedByEvenness = numbers.groupBy(_ % 2 == 0)
    println(groupedByEvenness)

    // find
    val firstEven = numbers.find(_ % 2 == 0)
    println(firstEven)

    // exists
    val existsGreaterThan5 = numbers.exists(_ > 5)
    println(existsGreaterThan5)

    // forall
    val allLessThan10 = numbers.forall(_ < 10)
    println(allLessThan10)

    // sort
    val sortedNumbers = numbers.sortBy(n => n * n)
    println(sortedNumbers)

    // for-comprehensions
    val resultWithMap = numbers.map(n => n * n).filter(squared => squared % 2 == 0)
    println(resultWithMap)

    //yield는 for 루프의 각 반복에서 생성된 값을 새로운 컬렉션에 추가
    val resultWithComp = for {
        n <- numbers
        squared = n * n
        if squared % 2 == 0
    } yield squared
    println(resultWithComp)

    val alpha = List('a', 'b', 'c')
    var combination = for {
        n <- numbers
        a <- alpha
    } yield a + "-" + n
    println(combination)


}
