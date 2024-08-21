fun main() {
    var obj1 = TestClass1(100, 200)
    var obj2 = TestClass2(100, 200)

    println("obj1.a1 : ${obj1.a1}")
    println("obj1.a2 : ${obj1.a2}")

    println("obj2.a1 : ${obj2.a1}")
    println("obj2.a2 : ${obj2.a2}")

    // 보조 생성자
    var obj3 = TestClass1(100, 200, 300);
    var obj4 = TestClass2(100, 200, 300);

    println("obj3.a1 : ${obj3.a1}")
    println("obj3.a2 : ${obj3.a2}")
    println("obj3.a3 : ${obj3.a3}")

    println("obj4.a1 : ${obj4.a1}")
    println("obj4.a2 : ${obj4.a2}")
    println("obj4.a3 : ${obj4.a3}")

    obj3.testMethod1()
    obj4.testMethod2()

    println("------------------------")

    var obj5 = TestClass1(100, 200, 300);
    var obj6 = TestClass1(100, 200, 300);

    // 다른 객체로 인식 - 객체의 주소값이 다름
    if (obj5 == obj6) {
        println("obj5와 obj6은 같은 객체 입니다.")
    } else {
        println("obj5와 obj6은 다른 객체 입니다.")
    }

    var obj7 = TestClass2(100, 200, 300);
    var obj8 = TestClass2(100, 200, 300);

    // 같은 객체로 인식
    // Data class -> == -> equals를 자동으로 처리
    // 주생성자로 값을 비교
    if (obj7 == obj8) {
        println("obj7와 obj8은 같은 객체 입니다.")
    } else {
        println("obj7와 obj8은 다른 객체 입니다.")
    }

    println("------------------------")

    // 구현 X
//    val obj9 = obj5.copy();

    // 주생성자에 정의되어진 맴버로만 사용 가능
    // a3 -> 보조 생성자라 해당 내용은 관여 X
    val obj10 = obj7.copy()
    println("obj10.a1 : ${obj10.a1}")
    println("obj10.a2 : ${obj10.a2}")
    println("obj10.a3 : ${obj10.a3}")

    // 복사를 해서 사용해도 객체를 서로 공유하지 않음
    obj10.a1 = 1000
    println("obj7.a1 : ${obj7.a1}")
    println("obj10.a1 : ${obj10.a1}")

    println("------------------------")

    val str1 = obj5.toString()
    val str2 = obj7.toString()

    println("str1 : $str1") // 주소값이 찍힘
    println("str2 : $str2") // 맴버변수의 값이 나옴(주생성자에 정의한 값)

    println("------------------------")

    val num1 = obj7.component1()
    val num2 = obj7.component2()

    println("num1 : $num1")
    println("num2 : $num2")

    println("------------------------")

//    var obj11 = TestClass3(100, 200, 300);
//    val num3 = obj11.component1()
//    val num4 = obj11.component2()
//    val num5 = obj11.component3()
//
//    println("num3 : $num3")
//    println("num4 : $num4")
//    println("num5 : $num5")

    val (num10, num11) = obj7
    println("num10 : $num10")
    println("num11 : $num11")

}

class TestClass1(var a1: Int, var a2: Int) {

    var a3: Int = 0

    init {
        println("TestClass1의 init")
    }

    // this -> 주생성자 호출
    constructor(a1: Int, a2: Int, a3: Int) : this(a1, a2) {
        this.a3 = a3
    }

    fun testMethod1() {
        println("TestClass1의 testMethod2 입니다.")
    }


}

// data class는 data를 관리하기 때문에 주 생성자가 필수
data class TestClass2(var a1: Int, var a2: Int) {

    var a3: Int = 0

    init {
        println("TestClass2의 init")
    }

    // this -> 주생성자 호출
    constructor(a1: Int, a2: Int, a3: Int) : this(a1, a2) {
        this.a3 = a3
    }

    fun testMethod2() {
        println("TestClass2의 testMethod2 입니다.")
    }

}

class TestClass3(var a1: Int, var a2: Int, var a3: Int) {}