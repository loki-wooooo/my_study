fun main() {
    test1();

    //값으로 작성시 매개 변수에 내용을 보여줌
    //intellij 기본 기능
    test2(100, 11.11);

    val k1: Int = 100
    val k2: Double = 11.11
    test2(k1, k2);

    test2(500, 55.55);

    // 순서를 변경해서 입력을 받을 수 있음.
    test2(a2 = 66.66, a1 = 100);

    test3(100, 11.11);
    test3(100);
    test3(a2 = 22.22);


    val r1: Int = test4(100, 200)
    val r2: Int = test4(1000, 2000)
    println("r1 : $r1")
    println("r2 : $r2")
    println("----------------------")

    test5();

    test6();

    test7()
    test7(100)
    test7(11.11)
    test7(100, 200)

    test8()

}

// 기본함수
fun test1() {
    println("test1 함수 호출")
    println("----------------------")
}

fun test2(a1: Int, a2: Double) {
    println("test2 함수 호출")
    println("a1 : $a1")
    println("a2 : $a2")
    println("----------------------")
}

fun test3(a1: Int = 0, a2: Double = 0.0) {
    println("test3 함수 호출")
    println("a1 : $a1")
    println("a2 : $a2")
    println("----------------------")
}

fun test4(a1: Int, a2: Int): Int {
    val result: Int = a1 + a2
    return result
}

// java -> void와 같은 개념
// Unit -> 생략 가능
fun test5(): Unit {
    println("test5 함수 호출")
    println("----------------------")
}

fun test6() {
    println("test6 함수 호출")
    println("----------------------")
}

/**
 * 함수의 오버로딩
 * */
fun test7() {
    println("test7 함수 호출 - 매개변수 없음")
    println("----------------------")
}

fun test7(a1:Int) {
    println("test7 함수 호출 - 매개변수 한개(Int)")
    println("a1 : $a1")
    println("----------------------")
}

fun test7(a1:Double) {
    println("test7 함수 호출 - 매개변수 한개(Double)")
    println("a1 : $a1")
    println("----------------------")
}

fun test7(a1:Int, a2:Int) {
    println("test7 함수 호출 - 매개변수 두개(Int, Int)")
    println("a1 : $a1")
    println("a2 : $a2")
    println("----------------------")
}

/**
 * 지역 함수
 * 함수 내에 정의한 함수.
 * 함수를 정의한 함수 안에서만 호출이 가능하다.
 * java - innerclass와 비슷한듯.
 * */

fun test8():Unit {
    println("test8 함수 호출")

    fun test9() {
        println("test9 함수 호출")
    }

    test9()
    println("----------------------");
}