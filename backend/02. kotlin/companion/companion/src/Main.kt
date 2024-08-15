fun main() {

    var obj1 = TestClass();
    println("obj1.a1 : ${obj1.a1}")
    obj1.testFun1();

    var obj2 = TestClass()
    println("obj2.a1 : ${obj1.a1}")
    obj2.testFun1();

    obj1.a1 = 200
    println("obj1.a1 : ${obj1.a1}")
    println("obj2.a1 : ${obj2.a1}")

    // 참조변수 접근 X
//    println("obj1.a2 : ${obj1.a2}")
//    obj1.testFun2()

    // 객체 생성없이 바로바로 사용이 가능함
    println("TestClass.a2 : ${TestClass.a2}")
    TestClass.testFun2()


    val obj3 = JavaMain()
    println("obj3.javaA1 : ${obj3.javaA1}")
    obj3.javaMethod1()

    println("JavaMain.javaA2 : ${JavaMain.javaA2}")
    JavaMain.javaMethod2()
}

/*
 * class TestClass - 일반 객체
 */
class TestClass {
    var a1 = 100

    // 객체를 생성하지 않아도 메모리상 해당 내용이 만들어져 있음.
    companion object {
        var a2 = 1000

        //@JvmStatic 사용시 java에서 사용할때 "companion" 키워드 생략 가능
        @JvmStatic
        var a3 = 2000

        fun testFun2() {
            println("testFun2")

            //companion 외부에 있는 변수는 사용 X
            //외부 변수는 객체가 생성이 되어야 하기 때문에
            //println("a1 : $a1")

            println("a2 : ${a2}")
        }

        @JvmStatic
        fun testFun3() {
            println("testFun3")
        }
    }

    fun testFun1() {
        println("testFun1")
        println("a1 : $a1")

        // 둘다 사용 가능
        println("a2 : $a2")
        testFun2()
    }
}

