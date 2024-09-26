fun main() {
    testFunction1()

    // 함수의 주소값을 변수로 담을 수 없음
//    val testFunction2 = testFunction1

    // 익명함수
    // 함수에 직접 접근 X
    val testFunction2 = fun() {
        println("Test function2")
    }
    testFunction2()

        
}

fun testFunction1(){
    println("Test function1")
}