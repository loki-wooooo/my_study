fun main() {

    val obj1 = TestClass1(100, 200)
    println("obj1.a1: ${obj1.a1}")
    println("obj1.a2: ${obj1.a2}")

    obj1.a1 = 1000
    println("obj1.a1: ${obj1.a1}")

    println("----------------------------------------")

    val obj2 = TestClass2()
    obj2.v1 = 100
    //Setter 가 없음(final integer)
//    obj2.v2 = 200

    println("obj2.v1: ${obj2.v1}")
    println("obj2.v2: ${obj2.v2}")
}

// constructor() -> () constructor 생략가능
// 주 생성자에서만 var, val 사용가능
class TestClass1(var a1: Int, val a2: Int){
    
    // 보조 생성자에는 val, var사용 X
//    constructor(var a1:Int, val a2:Int, val a3:Int) : this(a1, a2){}

}

class TestClass2{
    var v1:Int = 0
    val v2:Int = 0
    
    // 특정 조건에 따른 getter/setter 직접 구현 가능함
    var v3:Int = 0
        //get() = field // field -> V3 -> 값을 반환
        get() {
            println("get 호출")
            return field
        }
        set(value) {
            println("set 호출")
            field = value
        }
}