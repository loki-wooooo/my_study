fun main() {

    // 추상 클래스는 직접 객체를 생성할 수 없음
//    val obj1 = Super1()
//    testFun1(obj1)

    // override를 했기 떄문에 부모를 통해 자식의 내용을 보여줌
    val obj2 = Sub1()
    testFun1(obj2)

    // override를 하지 않았기 때문에 부모에 내용을 호출함
    val obj3 = Sub2()
    testFun1(obj3)

}

open abstract class Super1 {

    fun method1() {
        println("Super1의 method1입니다.")

    }

//    open fun method2() {
//        println("Super1의 method2입니다.")
//    }

    open abstract fun method2()

}

class Sub1 : Super1() {

    override fun method2() {
        println("Sub1의 method2입니다.")
    }
}

// override 안했을 시 -> 부모
// 추상 매서드를 정의 해야함
// 강제 override 처리
class Sub2 : Super1() {

    override fun method2() {
        println("Sub2의 method2입니다.")
    }
}


//function 생성
//Super1타입의 객체를 넘겨줘야함
fun testFun1(obj1: Super1) {
    obj1.method1()
    obj1.method2()
}