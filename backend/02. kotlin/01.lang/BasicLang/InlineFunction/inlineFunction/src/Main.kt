fun main() {
    testFunction1()
    testFunction1()

    testFunction2()
    testFunction2()
}

// 일반 함수
fun testFunction1() {
    println("------------------------")
    println("testFunction1")
    println("------------------------")
}

// inline 함수
// java 에서는 testFunction2의 값을 호출 하는 곳에 붙여서 넣음
inline fun testFunction2() {
    println("------------------------")
    println("testFunction2")
    println("------------------------")
}