fun main() {

    // 정수형 리터럴
    println(100);

    // java -> long형식으로 변경해야하는 kotlin은 "l"안붙여도 자동으로 l이 붙음, "l" 명시도 가능
    println(100000000000)

    // 3자리 숫자로 표현도 가능함(보기 편하게)
    println(190_283_109_249)

    // 실수형 리터럴
    // double
    println(11.11)

    // float
    println(22.22F)

    // 문자 리터럴
    println('A')
    println('가')

    // 문자열 리터럴
    println("문자열")

    // Boolean 논리 리터럴
    println(true)
    println(false)

    /**
     * Raw string: """문자열"""
     * Nothing: 함수가 정상적으로 끝나지 않는다를 의미
     * Unit: void를 의미한다.
     * */

    // Row String
    println("동해물과 백두산이\n마르고 닳도록\n 하느님이 보우사하\n 우리나라 만세")
    println("""동해물과 백두산이
        |마르고 닳도록
        |하느님이 보우하사
        |우리나라만세
    """.trimMargin())

    /**
     * kotlin은 기본자료형을 객체로 만들어서 사용함
     * 자료형이란?
     *  Long, Int, Short, Byte
     *  ULong UInt UShort UByte
     *  Double(8) Float(4)
     *  Boolean(1)
     *  Char(2)
     *  String
     * */

    /**
     * 변수 선언
     * Kotlin은 변수 선언시 val, var 두 가지 키워드를 사용
     * var : 선언 이후 값을 다시 저장할 수 있다.(= 변수)
     * val: 선언 이후 값을 다시 저장할 수 없다.(= 상수)
     * var/val 변수명:타입 = 값
     * 타입을 생략 시 저장하는 값에 따라 자료형이 자동으로 결정된다.
     *
     * "kotlin은 자료형 변수의 타입을 생략하는 걸 추천함"
     * */

    val a1:Int = 100
    //println("a1 : ${a1}")
    println("a1 : $a1") // 중괄호 생략 가능 변수만 있을시

    val a2 = 100
    println("a2 : $a2")

    println("=====================================================")

    var a3:Int = 100
    // val 변수는 값을 다시 저장하지 못한다.(final 개념)
    val a4:Int = 100

    println("a3 : $a3")
    println("a4 : $a4")

    a3 = 200
    println("a3 : $a3")

//    a4 = 200
    println("=====================================================")


    /**
     *  null 허용 변수
     *  Kotlin은 변수를 선언 할 때 null 허용 여부를 설정할 수 있다.
     *  var/val 변수명:자료형 = 값 - null을 허용하지 않는 변수
     *  var/val 변수명:자료형? =  값 - null을 허용하는 변수
     *
     * */
//    var a5:Int = null //null을 셋팅하지 못함
    var a5:Int = 100
    var a6:Int? = null //null을 셋팅할수 있음

    println("a5 : $a5")
    println("a6 : $a6")

    var a7:Int? = a5 // null+ 정수
    println("a7 : $a7")

//    var a8:Int = a6 // null을 허용하지 못하기 때문에 변수에 null값을 줄수 없다.
    // !!입력 시 null을 허용하지 않는 변수로 변경해주지만, 실제로는 null이 들어가서 에러가 남
    var a8:Int = a6!!
}