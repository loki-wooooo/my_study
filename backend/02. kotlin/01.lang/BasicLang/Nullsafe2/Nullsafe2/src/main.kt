fun main() {

    testMethod1("안녕하세요")
    testMethod1(null)

    println("-----------------------------")

    testMethod2("안녕하세요")
    testMethod2(null)
}

fun testMethod1(str: String?) {

    //null이면 값이 없기 떄문에 에러
//    println(str.length)
    // 원래는 null을 허용하지만 현재 !!을 통해 null을 허용하지 않는 변수로 변
//    println(str!!.length)

    // 해당 값이 null이면 "length" 함수를 사용안하고 그냥 null 반환
    println(str?.length)

    if (str is String) {
        // 스마트 캐스팅 발생
        println(str.length)
    }

    if (str != null) {
        // 스마트 캐스팅 발생
        println(str.length)
    }
}

fun testMethod2(str: Any?) {

    // 비교연산자보다는 "is"를 이용해서 하는게 좋음
    if (str is String) {
        println(str.length)
    }


//    if (str != null){
//        // string 타입을 검사하는게 없어서 에러
//        print(str.length)
//    }

}