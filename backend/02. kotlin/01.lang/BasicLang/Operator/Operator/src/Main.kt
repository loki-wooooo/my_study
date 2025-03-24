fun main() {
    // 단항 연산자 : 항이 하나인 연산자

    // + : 양수 -> 양수, 음수 -> 음수
    val a1:Int = 10;
    val a2:Int = -10;

    val r1:Int = +a1;
    val r2:Int = +a2;

    println("a1 : $a1, r1 : $r1")
    println("a2 : $a2, r2 : $r2")

    // - : 양수 -> 음수, 음수 -> 양수
    val r3:Int = -a1;
    val r4:Int = -a2;


    println("a1 : $a1, r3 : $r3")
    println("a2 : $a2, r4 : $r4")


    // ! : true -> false, false -> true
    val a3:Boolean = true
    val a4:Boolean = false

    val r5:Boolean =!a3
    val r6:Boolean =!a4

    println("a3 : $a3, r5 : $r5")
    println("a4 : $a4, r6 : $r6")

    println("---------------------------------------------")

    var a5:Int = 10
    var a6:Int = 10
    val r7:Int = a5++ //=> a5 = a5 +1(리터럴 아닌 변수로)
    val r8:Int = a6-- //=> a6 = a6-1(리터럴 아닌 변수로)

    println("a5 : $a5, r7 : $r7");
    println("a6 : $a6, r8 : $r8");

    var a7:Int = 10
    var a8:Int = 10

    val r9:Int = ++a7
    val r10:Int = --a8
    println("a7 : $a7, r9 : $r9");
    println("a8 : $a8, r10 : $r10");

    println("---------------------------------------------")

    val r11:Int = 10 + 3
    val r12:Int = 10 - 3
    val r13:Int = 10 * 3
    val r14:Int = 10 / 3
    val r15:Int = 10 % 3
    println("$r11 $r12 $r13 $r14 $r15")

    // 10 ~ 20 까지의 값의 범위
    val r16:IntRange = 10..20
    println("$r16")

    println("---------------------------------------------")

    var a9:Int = 10
    var a10:Int = 10
    var a11:Int = 10
    var a12:Int = 10
    var a13:Int = 10


    a9 += 3
    a10 -= 3
    a11 *= 3
    a12 /= 3
    a13 %= 3

    println("$a9, $a10, $a11, $a12, $a13")

    println("---------------------------------------------")

    var a14:Int = 10
    val r17:Boolean = a14 == 10
    val r18:Boolean = a14 != 10

    println("$r17 $r18")

    val r19:Boolean = a14 == 20
    val r20:Boolean = a14 != 20

    println("$r19 $r20")

    println("---------------------------------------------")

    val a15:Int = 10

    val r21:Boolean = a15 < 20
    val r22:Boolean = a15 > 20
    val r23:Boolean = a15 <= 10
    val r24:Boolean = a15 >= 10

    println("$r21, $r22, $r23, $r24")

}