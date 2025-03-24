fun main() {
    val obj1: SubClass1 = SubClass1()
    val obj2: SubClass2 = SubClass2()

    val obj3: SuperClass1 = obj1
    val obj4: Inter1 = obj2

    println("obj3 : $obj3")
    println("obj4 : $obj4")

//    obj3.subMethod1()    <<< 오류 발생
//            obj4.subMethod2()    <<< 오류 발생

    obj3 as SubClass1
    obj3.subMethod1() // SubClass1의 subMethod1입니다.

    obj4 as SubClass2
    obj4.subMethod2() // SubClass2의 subMethod2입니다.

    val temp2: SubClass3 = SubClass3()

//    temp2 as SuperClass1    <<< 오류 발생
//            temp2 as Inter1    <<< 오류 발생

    val obj5: SubClass1 = SubClass1()

    val temp3: SuperClass1 = obj5
    val temp4 = obj5 as SuperClass1

    println("temp3 : $temp3") // SubClass1@ ~~~
    println("temp4 : $temp4") // SubClass1@ ~~~

    val chk1 = temp4 is SubClass1
    val chk2 = temp4 is SuperClass1
    val chk3 = temp4 is Inter1

    println("chk1 : $chk1") // true
    println("chk2 : $chk2") // true
    println("chk3 : $chk3") // false

    val number1: Int = 100
    val number2: Long = number1.toLong()
    println("number2 : $number2")

    val str1: String = "100"
    val number3: Int = str1.toInt()
    println("number3 : $number3")

    // 스마트 캐스팅
    val super3: SuperClass1 = SubClass1()

    if (super3 is SubClass1) {
        super3.subMethod1()
    }

//    super3.subMethod1() < < < 오류 발생

    val obj10 = SubClass1()
    val obj11 = SubClass2()
    val obj12 = 100
    val obj13 = "문자열"

    anyMethod(obj10)
    anyMethod(obj11)
    anyMethod(obj12)
    anyMethod(obj13)

    val str2: String = "안녕하세요"
//    val number4:Int = str2.toInt()    <<< 오류 발생
//    println("number4 : $number4")
}

open class SuperClass1
interface Inter1

class SubClass1 : SuperClass1() {
    fun subMethod1() {
        println("SubClass1의 subMethod1입니다.")
    }
}

class SubClass2 : Inter1 {
    fun subMethod2() {
        println("SubClass2의 subMethod2입니다.")
    }
}

class SubClass3

fun anyMethod(obj: Any) {
    if (obj is SubClass1) {
        obj.subMethod1()
    }
    if (obj is SubClass2) {
        obj.subMethod2()
    }
    if (obj is Int) {
        println("정수입니다")
    }
    if (obj is String) {
        println("문자열 입니다")
    }
}
출처: https://wise-99.tistory.com/71?category=1106632 [안드로이드 개발자의 창고:티스토리]