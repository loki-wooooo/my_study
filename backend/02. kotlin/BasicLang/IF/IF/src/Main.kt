fun main() {

    val a1: Int = 10

    // 기본 if 문 : if 문의 조건식(변수의 값)이 true인 경우에만 내부의 코드가 실행
//    if (a1 == 10) {
//        println("a1은 10 입니다.")
//    }
//
//    if (a1 != 10) {
//        println("a1은 10 아닙니다.")
//    }

    // else 문 : 조건식이 만족하지 않을 경우 수행될 부분
    if (a1 == 10) {
        println("a1은 10 입니다.")
    } else {
        println("a1은 10 아닙니다..")
    }

    if (a1 == 20) {
        println("a1은 20 입니다.")
    } else {
        println("a1은 20 아닙니다.")
    }

    // else if 문
    if (a1 == 5) {
        println("a1은 5 입니다.")
    } else if (a1 == 10) {
        println("a1은 10 입니다.")
    } else if (a1 == 20) {
        println("a1은 20 입니다.")
    } else {
        println("a1은 5, 10, 20이 아닙니다.")
    }

    val a2: Int = 10;
    val a3: Int = 20;

    // 모든 조건을 만족해야할 경우
    if (a2 == 10 && a3 == 20) {
        println("a2는 10이고, a3은 20입니다.")
    }

    if (a2 == 10 && a3 == 30) {
        println("a2는 10이고, a3은 30입니다.")
    }

    // 주어진 조건 중 하나만 만족하면 될 경우
    if (a2 == 10 || a3 == 30) {
        println("a2는 10이거나 a3은 30입니다.")
    }

    if (a2 == 20 || a3 == 10) {
        println("a2는 20이거나 a3은 10입니다.")
    }

    // if문을 활용한 변수 입력
    println("-------------------------------")

    var a4: String = ""

    var a5: Int = 10

    if (a5 == 10) {
        a4 = "10 입니다."
    } else {
        a4 = "10이 아닙니다."
    }
    println("a4 : $a4")


    val a6: String = if (a5 == 10) "10 입니다." else "10이 아닙니다."
    println("a6 : $a6")

    val a7: String = if (a5 == 10) {
        //마지막 값이 해당 변수에 담김
        println("블록 1 수행")
        "10 입니다."
    } else {
        println("블록 2 수행")
        "10이 아닙니다."
    }
    println("a7 : $a7")

}