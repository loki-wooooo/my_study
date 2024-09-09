import javax.security.auth.Subject

fun main() {

    val obj1: SubClass1 = SubClass1()
    val obj2: SubClass2 = SubClass2()

    // 부모 클레스 타입 참조변수에 담는다.
    // 스마트 케스팅 발생
    val super1: SuperClass1 = obj1
    // 스마트 케스팅 발생
    val inter1: Inter1 = obj2

    println("-------------------------")

    // as : 지정된 클레스 타입으로 강제 변환하는 연산자
    // subclass 타입으로 강제 형 변환
    // interface 구현, 자기를 상속받은 자식의 참조변수로 변환할 시 "as" 사용
    super1 as SubClass1
    inter1 as SubClass2

    inter1 as SubClass1

//    super1.subMethod1()
//    inter1.subMethod2()

    println("-------------------------")

    val obj3: SubClass1 = SubClass1()

    val chk1 = obj3 is SuperClass1
    println("chk1 $chk1")

    // 형변환이 맞지 않아 에러
//    val chk2 = obj3 is Inter1
    val super3: SuperClass1 = obj3
    val chk3 = super3 is SubClass1
    println("chk3 $chk3")

    // is 를 썻다고 형변환이 나오지 않음
//    super3.subMethod1()
    if (super3 is SubClass1) {
//        super3 as SubClass1

        // is 연산자 값이 true면 형변환이 가능한 상황이므로 스마트 케스팅 발생

        super3.subMethod1()
    }

    println("-------------------------")

    val obj10: SubClass1 = SubClass1()
    val obj11: SubClass2 = SubClass2()

    anyMethod(obj10)
    anyMethod(obj11)
    anyMethod(100)
    anyMethod("문자열")

    println("-------------------------")

    val number1:Int = 100
    val number2:Long = number1.toLong()
    println("number2: $number2")

    val string1:String = "100"
    val number3:Int = string1.toInt()
    println("number3: $number3")

}

open class SuperClass1
interface Inter1

class SubClass1 : SuperClass1() {
    fun subMethod1() {
        println("Subclass1의 SubMethod1 입니다.")
    }
}

class SubClass2 : Inter1 {
    fun subMethod2() {
        println("Subclass2의 SubMethod2 입니다.")
    }
}

fun anyMethod(obj: Any) {
    if (obj is SubClass1) {
        obj.subMethod1()
    } else if (obj is SubClass2) {
        obj.subMethod2()
    } else if (obj is Int) {
        println("정수 입니다.")
    } else if (obj is String) {
        println("문자열 입니다.")
    }
}