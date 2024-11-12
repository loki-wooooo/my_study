fun main() {

    /**
     * return - 호출한 쪽으로 되돌아 가겠다.
     * */
    val r1 = testFun1(100)
    println("r1 : $r1")

    val r2 = testFun2(2)
    println("r2 : $r2")

    val r3 = testFun2(0)
    println("r3 : $r3")

    testFun3(100)
    testFun3(0)

    println("-----------------------------------")

    // break -> 반복 횟수가 남아 있더라도 반복을 중단
    for (item in 1..10) {
        if (item > 5) {
            break;
        }
        println("item : $item")
    }

    println("-----------------------------------")

    // continue -> 반복문에서 코드가 남아 있더라도 다음 반복으로 넘어간다.
    for (item in 1..10) {
        if (item  % 2 == 0) {
            continue
        }
        println("item : $item")
    }

}

fun testFun1(a1: Int): Int {
    println(" testFun1")
    return a1 + 100
}

fun testFun2(a1: Int): Int {
    println(" testFun2")
    if (a1 == 0) {
        return -1
    }

    println("요 부분이 수행 될까요?")

    return 100 / a1
}

fun testFun3(a1: Int): Unit {
    if (a1 == 0) {
        return
    }
    println("a1은 0이 아닙니다.")
}