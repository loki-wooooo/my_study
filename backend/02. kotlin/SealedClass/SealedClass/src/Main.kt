fun main() {
    val v1 = Number.TWO
    checkNumber(v1)
    println("------------------------------------")

    // 값을 서로 다르게 구성할 수 있음.
    val v2 = Number2.SealedTwo(1)
    val v3 = Number2.SealedTwo(2)
    val v4 = Number2.SealedOne(100, 200)
    val v5 = Number2.SealedThree(100, 11.11)

    checkNumber2(v2)
    checkNumber2(v3)
    checkNumber2(v4)
    checkNumber2(v5)

}

enum class Number(val num: Int) {
    ONE(1), TWO(2), THREE(3)
}

fun checkNumber(a1: Number) {

    when (a1) {
        Number.ONE -> println("One")
        Number.TWO -> println("Two")
        Number.THREE -> println("Three")
    }

    when (a1.num) {
        1 -> println("One")
        2 -> println("Two")
        3 -> println("Three")
    }
}

sealed class Number2 {
    class SealedOne(val a1: Int, val a2: Int) : Number2()
    class SealedTwo(val a1: Int) : Number2()
    class SealedThree(val a1: Int, val a2: Double) : Number2()
}

fun checkNumber2(obj1: Number2) {
    when (obj1) {
        is Number2.SealedOne -> {
            println("One")
            println(obj1.a1)
            println(obj1.a2)
        }

        is Number2.SealedTwo -> {
            println("Two")
            println(obj1.a1)

            when (obj1.a1) {
                1 -> println("One")
                2 -> println("Two")
            }
        }

        is Number2.SealedThree -> {
            println("Three")
            println(obj1.a1)
            println(obj1.a2)
        }
    }

}