fun main() {

    val obj1 = TestClass1()
    println("obj1.a1 : ${obj1.a1}")
    println("obj1.a2 : ${obj1.a2}")

    // error :: 초기화가 되어있지 않아서 에러남
    // 변수를 사용할 떄는 무조건 값의 초기화가 되어있어야함
//    println("obj1.a3 : ${obj1.a3}")
    obj1.testMethod1();

    println("obj1.a4 : ${obj1.a4}")
}

class TestClass1 {

    // 변수의 값을 추론이 가능하면 자료형의 생략이 가능함
    var a1: Int = 100

    //값을 초기화 후 나중에 값을 직접 입력
    // 해당 값은 변수가 없어서 추론이 불가능해 해당 변수타입을 써야함
    var a2: Int

    // 기본자료형이 아닌 타입만 가능함(Int, Long ..)
    lateinit var a3: String

    // a4를 한번도 실행하지 않으면 해당 변수는 생성이 돼지 않음.
    val a4: String by lazy {
        println("a4 init")
        "문자열2"
    }

    init {
        a2 = 200
    }

    fun testMethod1() {

        //:: -> reflection
        //
        if (::a3.isInitialized == false) {
            a3 = "문자열"
        }
        println("a3 : ${a3}")
    }
}