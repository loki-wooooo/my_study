fun main() {

    val obj1 = Outer1()
    val obj2 = obj1.Inner1()

    obj2.innerMethod2()

    obj1.outerMethod2()

    println("----------------")

    val t1 = TestClass2()
    t1.testMethod1()

    val t2 = TestClass3()
    t2.interMethod1()

    //익명 중첩
    val t3 = object : TestClass1() {
        override fun testMethod1() {
            println("t3 익명 중첩 클래스 입니다.")
        }
    }
    t3.testMethod1()

    val t4 = object : TestInter1{
        override fun interMethod1() {
            println("t4 익명 중첩 클래스 입니다.")
        }
    }
    t4.interMethod1()
}

/**
 * inner -> outer 사용 O
 * outer -> inner 사용 X(객체 생성 필요)
 * */
class Outer1 {

    val outerMember1 = 100

    fun outerMethod1() {
        println("outerMethod1")
    }

    fun outerMethod2() {
        // Inner1의 클래스를 생성하지 않아서 에
//        println("innerMethod1 : $innerMethod1")

        val obj3 = Inner1()
        println("obj3.innerMethod1 : ${obj3.innerMember1}")
        obj3.innerMethod1()
    }

    //outer 클래스의 객체 생성 후 inner를 생성 가능
    inner class Inner1 {
        // 안의 메서드 내용을 만드는건 Inner1를 생성

        val innerMember1 = 200

        fun innerMethod1() {
            println("innerMethod1")
        }

        fun innerMethod2() {
            println("outerMember1 : $outerMember1")
            outerMethod1()
        }


    }
}

// 추상 클래스 상속
abstract class TestClass1 {
    abstract fun testMethod1()
}

interface TestInter1 {
    fun interMethod1()
}

class TestClass2 : TestClass1() {

    override fun testMethod1() {
        println("testMethod1")
    }
}

class TestClass3 : TestInter1 {

    override fun interMethod1() {
        println("interMethod1")
    }

}