import kr.co.softcampus.pkg1.TestClass2
import kr.co.softcampus.pkg1.testFunction2

import kr.co.softcampus.pkg2.*

fun main() {

    // 다른 package의 객체 생성
    // obj1:kr.co.softcampus.pkg1.TestClass1 -> 타입 생략 가능 kr.co.softcampus.pkg1.TestClass1
    // import 사용시 package명을 생략이 가능함
    val obj1: kr.co.softcampus.pkg1.TestClass1 = kr.co.softcampus.pkg1.TestClass1()
    obj1.testMethod1()
    println("------------------------------------")

    kr.co.softcampus.pkg1.testFunction1()
    println("------------------------------------")


    //TestClass2 -> 타입지정으로 인한 생략 가능
    val obj2: TestClass2 = TestClass2()
    obj2.testMehtod2()
    testFunction2();
    println("------------------------------------")

    // 클래스의 명을 다르게 사용하는게 일반적으로 좋음.
    val obj3 = TestClass3();
    val obj4 = TestClass4();

    obj3.testMethod3();
    obj4.testMethod4();
    println("------------------------------------")


}