fun main() {

    val t1 = fun(x1: Int, x2: Int): Int {
        return x1 + x2
    }
    testFunc1(t1, 100, 200)

    //익명함수 직접 등록
    testFunc1(fun(x1: Int, x2: Int): Int {
        return x1 + x2
    }, 100, 200)

    //lambda도 넘길수 있음
    val lambda1 = { x1: Int, x2: Int -> x1 * x2 }
    testFunc1(lambda1, 100, 200)
    testFunc1({ x1: Int, x2: Int -> x1 / x2 }, 200, 100)

    val t2 = testFun2()
    val r2 = t2(100, 200)
    println("r2: $r2")

    val t3 = testFun3()
    val r3 = t3(100, 200)
    println("r3: $r3")

    testFun4({ x1: Int -> x1 + 100 }, 200)

    // 매개변수가 1개만 있을떄
    // 타입 생략 후 it 사용
    testFun4({ it + 100 }, 200)

    // 사용 가능
    testFun5(100, 200, { x1: Int, x2: Int -> x1 + x2 })

    // lambda가 길경우 아래로 지원하기 위해
    // 문법적으로 지원
    // 고차함수를 쓸시 -> 함수를 받는 매개변수를 맨뒷쪽에 정의가 일반적
    // 맨 뒤에 함수를 정의시 "{}" 로 바로 사용가능
    testFun5(100, 200) { x1: Int, x2: Int -> x1 + x2 }

    testFun6({ x1: Int -> println(x1) })
    //매개변수가 1개밖에 없어서 "it"로 사용가능
    testFun6 { println(it) }
}

//m1: () -> Unit => 매개변수, 반환타입 X java void와 역할이 비슷함
fun testFunc1(m1: (Int, Int) -> Int, a1: Int, a2: Int) {
    val r1 = m1(a1, a2)
    println("r1 : $r1")
}

fun testFun2(): (Int, Int) -> Int {
    return fun(x1: Int, x2: Int): Int {
        return x1 + x2
    }
}

// lambda 형식
fun testFun3(): (Int, Int) -> Int {
    return { x1: Int, x2: Int -> x1 - x2 }
}

// lambda 형식
fun testFun4(m1: (Int) -> Int, a1: Int) {
    val r4 = m1(a1)
    println("r4 : $r4")
}

fun testFun5(a1: Int, a2: Int, m1: (Int, Int) -> Int) {
    val r5 = m1(a1, a2)
    println("r5 : $r5")
}

fun testFun6(m1: (Int) -> Unit) {
    m1(100)
}