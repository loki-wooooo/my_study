fun main() {
    val obj1 = TestClass1()
    println("obj1 : $obj1")

    println("----------------------------------------------")

    val obj2 = TestClass2()
    println("obj2 : $obj2")
    println("obj2.v1 : ${obj2.v1}")
    println("obj2.v2 : ${obj2.v2}")

    println("----------------------------------------------")

    val obj3 = TestClass2(100, 200);
    println("obj3 : $obj3")
    println("obj3.v1 : ${obj3.v1}")
    println("obj3.v2 : ${obj3.v2}")

    println("----------------------------------------------")

    val obj4 = TestClass3(100, 200);
    val obj5 = TestClass3(1000, 2000);

    println("obj4.v1 : ${obj4.a1}")
    println("obj4.v2 : ${obj4.a2}")

    println("obj5.v1 : ${obj5.a1}")
    println("obj5.v2 : ${obj5.a2}")

    println("----------------------------------------------")

    val obj6 = TestClass5(100, 200);
    println("obj6.v1 : ${obj6.a1}")
    println("obj6.v2 : ${obj6.a2}")

    println("----------------------------------------------")

    val obj7 = TestClass5(100);
    println("obj7.v1 : ${obj6.a1}")
    println("obj7.v2 : ${obj6.a2}")

}

class TestClass1 {

    //코드의 동작을 무조건 만들기 위해 "init"을 만듬
    init {
        println("객체가 생성되면 자동으로 동작되는 부분입니다.");
    }
}

class TestClass2 {
    var v1: Int = 0;
    var v2: Int = 0;

    // 부생성자
    constructor() {
        println("매개 변수가 없는 생성자");
    }
    
    // 부생성자
    constructor(a1: Int, a2: Int) {
        println("매개 변수가 두 개인 생성자")
        v1 = a1;
        v2 = a2;
    }
}

// constructor -> '기본생성자'는 생략 가능
// 주생성자
class TestClass3 constructor(var a1: Int, var a2: Int)
class TestClass4(var a1: Int, var a2: Int)

class TestClass5 (var a1:Int, var a2:Int){
    init {
        println("init 코드 수행")
        println("a1 : $a1")
        println("a2 : $a2")
    }

    // 보조 생성자는 무조건 주 생성자를 호출 해야함
    // 'var' 키워드는 사용할 수 없음
    // this(a1, 100) 주생성자 호출
    // 주생성자가 먼저 호출
    constructor(a1:Int) : this(a1, 100){
        println("보조 생성자 호출")

    }
}