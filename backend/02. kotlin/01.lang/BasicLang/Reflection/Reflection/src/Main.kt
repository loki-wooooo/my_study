import jdk.incubator.vector.VectorOperators.Test
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

fun main() {

    // 클래스 타입
//    println(String::class)  // kotlin type
//    println(String::class.java) // java type

    // kotlin class type -> kClass
    val a1: KClass<String> = String::class
    val a2: Class<String> = String::class.java

    println("String 코틀린 에서의 타입 :: $a1")
    println("String 자바 에서의 타입 :: $a2")

    val str1: String = "안녕하세요"
    // out -> 부모타입의 클래스로 변환
    val a3: KClass<out String> = str1::class
    val a4: Class<out String> = str1::class.java

    println("안녕하세요 코틀린 에서의 타입 :: $a3")
    println("안녕하세요 자바 에서의 타입 :: $a4")


    // reflection할 타입을 모를시 "*" 사용
    val a5: KClass<*> = str1::class
    val a6: Class<*> = str1::class.java

    println("안녕하세요 코틀린 에서의 타입 :: $a5")
    println("안녕하세요 자바 에서의 타입 :: $a6")


    val test1: TestClass = TestClass()
    val a7: KClass<*> = test1::class
    val a8: Class<*> = test1::class.java

    println("코틀린에서의 타입 :: $a7")
    println("자바에서의 타입 :: $a8")

    // 클래스 정보 분석
    println("추상 클래스 인가 :: ${test1::class::isAbstract}")
    println("Companion 클래스 인가 :: ${test1::class::isCompanion}")
    println("Data 클래스 인가 :: ${test1::class.isData}")
    println("final 클래스 인가 :: ${test1::class::isFinal}")
    println("open 클래스 인가 :: ${test1::class::isOpen}")
    println("중첩 클래스 인가 :: ${test1::class::isInner}")
    println("Sealed 클래스 인가 :: ${test1::class::isSealed}")

    // 생성자 정보 파악
    val constructors = test1::class.constructors
//    println(constructors)
    for (constructor in constructors) {
        println("constructor :: $constructor")

        for (param in constructor.parameters) {
            println("param index :: ${param.index}")
            println("param type :: ${param.type}")
            println("param name :: ${param.name}") // property name

        }
    }

    // 주 생성자
    // 주 생성자는 보통 1개거나 없기 떄문에 for를 돌려도 최대 하나밖에 없음
    val primaryConstructor = test1::class.primaryConstructor
    if (primaryConstructor != null) {
        println("primary constructor :: $primaryConstructor")

        for (param in primaryConstructor.parameters) {
            println("param index :: ${param.index}")
            println("param type :: ${param.type}")
            println("param name :: ${param.name}")
        }
    }

    // 맴버 변수(property)
    val properties = test1::class.declaredMemberProperties
    for (property in properties) {
        println("property name :: ${property.name}")
    }

    // 메서드 정보
    val methods = test1::class.declaredMemberFunctions
    for (method in methods) {
        println("method name :: ${method.name}")
    }
}

class TestClass() {
    val number1: Int = 100
    val number2: Int = 200

    // 보조 생성자
    constructor(a1: Int) : this() {
    }

    // 보조 생성자
    constructor(a1: Int, a2: Int) : this() {
    }

    fun testMethod1() {

    }
}