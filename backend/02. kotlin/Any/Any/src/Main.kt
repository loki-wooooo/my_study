fun main() {

    val obj1 = TestClass1()

    //$obj1 출력시 toString으로 출력함
    println("obj1 : $obj1")

    val obj2 = TestClass2()
    val obj3 = TestClass3()

    testFun1(obj1)
    testFun1(obj2)
    testFun1(obj3)
}

// any 상속 받음
class TestClass1 {
    override fun toString(): String {
        return "TestClass1의 객체 입니다."
    }
}

class TestClass2 {
    override fun toString(): String {
        return "TestClass2의 객체 입니다."
    }
}

class TestClass3 {
    override fun toString(): String {
        return "TestClass3의 객체 입니다."
    }
}

// 상속자체를 any 타입을 받고 있기 떄문에 해당 함수를 호출 할 수 있음
fun testFun1(a1: Any) {
    //자식쪽에 overriding 형식으로 호출함.
    println("a1 : $a1")
}