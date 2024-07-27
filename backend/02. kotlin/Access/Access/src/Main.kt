import ko.co.softcampus.pkg1.InternalClass2
import ko.co.softcampus.pkg1.PublicClass2
import ko.co.softcampus.pkg1.TestClass2
import kr.co.softcampus.module.pkg2.PublicClass3
import kr.co.softcampus.module.pkg2.TestClass3

fun main() {

    // private class는 외부에서 사용이 불가능함(상속도 불가능)
    val obj1 = PrivateClass1()
    val obj2 = PublicClass1();
    // 같은 모듈에서는 자유롭게 사용이 가능함
    val obj3 = InternalClass1();

    // private class는 외부에서 사용이 불가능함(상속도 불가능)
    val obj4 = PrivateClass2();
    val obj5 = PublicClass2();
    val obj6 = InternalClass2();

    // private class는 외부에서 사용이 불가능함(상속도 불가능)
    val obj7 = PrivateClass3();
    val obj8 = PublicClass3();

    // internal class는 모듈이 다르면 사용을 할 수 없음.
    val obj9 = InternalClass3();


    val t1 = TestClass1()
    // private 맴버는 사용 할 수 없음
    println("t1.private1 : ${t1.private1}")
    println("t1.public1 : ${t1.public1}")
    // protected는 상속관계일 때만 사용가능
    println("t1.protected1 : ${t1.protected1}")
    println("t1.internal1 : ${t1.internal1}")


    val t2 = TestClass2()
    // private 맴버는 사용 할 수 없음
    println("t2.private2 : ${t2.private2}")
    println("t2.public2 : ${t2.public2}")

    // protected는 상속관계일 때만 사용가능
    println("t2.protected2 : ${t2.protected2}")
    println("t2.internal2 : ${t2.internal2}")


    val t3 = TestClass3()
    // private 맴버는 사용 할 수 없음
    println("t3.private3 : ${t3.private3}")
    println("t3.public3 : ${t3.public3}")

    // protected는 상속관계일 때만 사용가능
    println("t3.protected3 : ${t3.protected3}")

    // internal 맴버는 같은 모듈이 아니기 때문에 사용이 불가
    println("t2.internal3 : ${t3.internal3}")

}

class SubClass1 : TestClass1(){
    fun subMethod1() {

        println("private1 : $private1")
        println("public1 : $public1")
        // protected는 상속관계일 때만 사용가능
        println("protected1 : $protected1")
        println("internal1 : $internal1")
    }
}

class SubClass2 : TestClass2(){
    fun subMethod2() {
        println("private2 : $private2")
        println("public2 : $public2")
        // protected는 상속관계일 때만 사용가능
        println("protected2 : $protected2")
        println("internal2 : $internal2")
    }
}

class SubClass3 : TestClass3(){
    fun subMethod3() {
        println("private3 : $private3")
        println("public3 : $public3")
        // protected는 상속관계일 때만 사용가능
        println("protected3 : $protected3")

        // 모듈이 다르기 때문에 사용할 수 없음
        println("internal3 : $internal3")
    }
}