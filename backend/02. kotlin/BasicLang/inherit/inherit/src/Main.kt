fun main() {
    val s1 = SubClass1();
    println("s1.subMember1: ${s1.subMember1}")
    s1.subMember1();

    println("s1.superMember1: ${s1.superMember1}")
    s1.superMethod1()
}

open class SuperClass1 {
    var superMember1 = 100

    fun superMethod1() {
        println("SuperClass1의 메서드 입니다.")
    }
}

// 부모클래스의 생성자를 정의해 줘야함
// SuperClass1() 매게변수 없는 생성자
class SubClass1 : SuperClass1() {
    val subMember1 = 100

    fun subMember1() {
        println("SubClass1의 메서드 입니다.")
    }

    //constructor() : super()

}

// 매게변수 있는 생성자
open class SuperClass2(val a1: Int)

class SubClass2 : SuperClass2(100)

class SubClass3 : SuperClass2 {
    constructor() : super(100)
}
