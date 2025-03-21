package lecture

/**
 * Immutable 컬렉션의 장단점
 * 장점:
     * 스레드 안전성: 여러 스레드에서 안전하게 공유 가능
     * 예측 가능한 동작: 상태 변경이 없어 디버깅이 쉬움
     * 함수형 프로그래밍 원칙에 부합
 *
 * 단점:
     * 변경 시 새 객체 생성으로 인한 메모리 사용량 증가 가능성
     * 대규모 데이터 구조에서 성능 저하 가능성
 * */
object ImmutableCollections extends App {

    // * Sequence
    val numbers: Seq[Int] = Seq(1, 2, 3, 4, 5) // companion object and returns List
    println(numbers)

    // Transform each number to its square
    val squaredNumbers: Seq[Int] = numbers.map(x => x * x)
    println(squaredNumbers)

    // Filter even numbers
    val evenNumbers: Seq[Int] = numbers.filter(n => n % 2 == 0)
    println(evenNumbers)

    // Append an element
    val extendedNumbers: Seq[Int] = numbers :+ 6
    println(extendedNumbers)

    // Prepend an element
    val prependedNumbers: Seq[Int] = 0 +: numbers
    println(prependedNumbers)

    // concatenate two sequences
    val moreNumbers: Seq[Int] = numbers ++ Seq(6, 7, 8)
    println(moreNumbers)

    // Accessing elements
    val firstNumber: Int = numbers(0)
    println(firstNumber)

    val secondNumber: Int = numbers(1)
    println(secondNumber)

    // Reverse
    val reversedNumber: Seq[Int] = numbers.reverse
    println(reversedNumber)

    // Sort
    val sortedNumber: Seq[Int] = Seq(8, 2, 3, 1, 4).sorted
    println(sortedNumber)

    // Range
    var rangeNumber: Seq[Int] = 1 to 10
    rangeNumber.foreach(i => print(s"${i} "))
    println("")

    // * List
    val firstList = List(1, 2, 3, 4, 5)
    println(0 :: firstList)

    // * Vector
    val vectors = Vector(1, 2, 3, 4, 5)
    val words = Vector("Scala", "Java", "Python")

    // vector immutable 이여서 신규 vector를 생성해서 리턴함
    val updatedNumbers = vectors.updated(0, 10)
    println(updatedNumbers)

    // 0부터 시작해서 왼쪽으로 접음
    val sum = vectors.foldLeft(0)(_ + _)
    println(sum)

    // 1부터 시작해서 접음
    val product = vectors.fold(1)(_ * _)
    println(product)

    // 오른쪽부터 시작,
    val sentence = words.foldRight(".")((word, acc) => {
        println(word + " " + acc)
        word + " " + acc
    })
    println(sentence)


}
