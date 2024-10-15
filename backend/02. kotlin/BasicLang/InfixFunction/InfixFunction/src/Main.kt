fun main() {

    val v1 = 100

    // 100 -> this , 50 -> a1
    val r1 = 100 add2 50
//    val r1 = v1.add2(50)
    println("r1 : $r1")


    val r2 = v1 add2 50
//    val r2 = v1.add2(50)
    println("r2 : $r2")

    val r3 = v1 minus2 50
    val r4 = v1.minus2(50)
    println("r4 : $r4")
}

infix fun Int.add2(a1: Int): Int = this + a1

infix fun Int.minus2(a2: Int): Int = this - a2