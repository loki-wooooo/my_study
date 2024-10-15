fun main() {

    val obj1: SubClass1 = SubClass1();
    println("obj1.subA1 : ${obj1.subA1}")
    obj1.subMethod1()

    println("obj1.superA1 : ${obj1.superA1}")
    obj1.superMethod1()

    println("--------------------------------------------------------")

    // 상속 관계 및 부모 클래스라서 해당 변수 선언이 가능
    val obj2: SuperClass1 = obj1

    println("obj2.superA1 : ${obj2.superA1}")
    obj2.superMethod1()

    /*
    * 해당 객체는 부모클래스 형태의 타입이기 떄문에 자식 클래스의 정의된 내용은 갖고올 수 없음.
    * */
//    println("obj2.subA1 : ${obj2.subA1}")
//    obj2.subMethod1()

    println("--------------------------------------------------------")

    val obj3: SubClass2 = SubClass2();
    obj3.superMethod2()

    val obj4: SuperClass2 = obj3

    //자식데이터를 호출 할 수 있음
    obj4.superMethod2()

    println("--------------------------------------------------------")

    /**
     * 자식 클래스에서 부모 클래스 메서드를 override를 했기 떄문에
     * 부모클래스를 참조하여, 자식클레스의 메서드를 호출함
     * */
    val obj5: SuperClass3 = SuperClass3();
    overridingTest(obj5)

    val obj6: SubClass3 = SubClass3();
    overridingTest(obj6)

}

// 부모 클래스 정의
open class SuperClass1 {
    var superA1 = 100

    fun superMethod1() {
        println("SuperClass1의 superMethod1 입니다.")
    }
}

// 자식 클래스 정의
class SubClass1 : SuperClass1() {
    var subA1 = 200

    fun subMethod1() {
        println("SubClass1의 subMethod1 입니다.")
    }
}

open class SuperClass2 {

    // 자식 class에서 재정의가 가능함
    open fun superMethod2() {
        println("SuperClass2의 superMethod2 입니다.")
    }
}

class SubClass2 : SuperClass2() {

    // 재구현
    override fun superMethod2() {
        println("SubClass2의 superMethod2 입니다.")
    }
}

open class SuperClass3 {
    open fun superMethod3() {
        println("SuperClass3의 superMethod3")
    }
}

class SubClass3 : SuperClass3() {

    override fun superMethod3() {
        //부모 클레스의 Method를 호출함
        super.superMethod3()
        println("SubClass3의 superMethod3")
    }
}

fun overridingTest(obj1: SuperClass3) {
    obj1.superMethod3()
}