fun main() {
    var obj1 = TestClass1()
    obj1.testMethod1()

    var obj2 = SubClass();
    obj2.subMethod1();
}

// 주생성자 "constructor" 추가
class TestClass1(var a2: Int) {

    // 반드시 주 생성자를 호출해 줘야함
    constructor() : this(100) {

    }

    var a1 = 100

    // 자기 자신게 우선
    fun testMethod1() {

        var a1 = 200

        println("a1: $a1")

        //객체가 갖고있는 변수를 가저 다 사용함(맴버변수)
        println("this.a1: ${this.a1}")

        fun testMethod2() {
            println("testMethod1 내부의 testMethod2")
        }

        testMethod2()
        this.testMethod2();
    }

    fun testMethod2() {
        println("testMethod2")
    }

}

open class SuperClass(var a2: Int) {
    open var a1 = 100;

    open fun superMethod1() {
        println("SuperClass의 superMethod1")
    }
}

// 부모클래스 생성자 반드시 호출
// 부모클래스에서 해당 생성자가 있으면 자식에서는 무조건 해당 생성자 매개변수를 넣어야함
class SubClass : SuperClass(100) {

    override var a1 = 1000; // 부모가 갖고있는 변수를 재정의 할 때 사용 "override"

    fun subMethod1() {
        println("a1 : $a1")
        println("super.a1: ${super.a1}")

        superMethod1()
        super.superMethod1()
    }

    override fun superMethod1() {
        println("SubClass의 superMethod1")
    }

}

// 생성자는 부모의 생성자를 갖고와서 생성해줘야함.
class SubClass2 : SuperClass {

    // 보조생성자에서 만들어서 넣음
    constructor(a1: Int) : super(a1) {

    }

    constructor(a1: Int, a2: Int) : super(a1) {

    }

}