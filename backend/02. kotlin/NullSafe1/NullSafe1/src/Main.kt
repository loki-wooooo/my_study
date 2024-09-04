fun main() {

    testFun1("문자열")
//    testFun1(null)

    println("------------------------------------------")

    testFun2("문자열2")
    testFun2(null)

    println("------------------------------------------")

    testFun3("문자")
    testFun3(null)
}

fun testFun1(str:String?) {
    // null을 허용하지 않는 값으로 변환
    val value1:String = str!!
    println("value1:  $value1")
}

fun testFun2(str:String?) {

    // 값이 null 이면 -> "기분 문자열" 출력
    val value1:String = str?: "기본 문자열"
    println("value1:  $value1")
}

fun testFun3(str:String?) {
    println("str:  $str")

    //null 이면 null, null이 아니면 파라미터 그대로 출력
    println("str length : ${str?.length}")
}