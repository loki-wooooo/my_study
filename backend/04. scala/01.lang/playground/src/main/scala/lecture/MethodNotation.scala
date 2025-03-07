package lecture

import scala.language.postfixOps

object MethodNotation extends App {

  class MyMember(var value: Int) {
    //infix notation
    def add(x: Int): Int = x + this.value

    def subtract(x: Int): Int = this.value - x

    // prefix notation(unary notation)
    // 단항 연산자를 정의하고 사용하는 방법
    // 커스텀 단항 연산자를 만들 때는 unary_ 접두사를 사용
    def unary_- : MyMember = new MyMember(-value)
    def unary_! : String = s"Sorry. not possible"

    // prefix
    def isZero: Boolean = this.value == 0

    // apply function
    // 객체를 함수처럼 호출할 수 있게 해줍
    // 코드를 더 간결하고 읽기 쉽게 만들어줌
    def apply(): String = s"MyMember is ${this.value}!"
  }

  //create an instance of the class
  val myMember = new MyMember(10)

  //infix notation to call methods (파라미터는 "1"개만 가능함)
  val result1 = myMember add 5 // equivalent to MyMember.add(5)
  val result2 = myMember subtract 8 // equivalent to MyMember.subtract(8)
  println(result1)
  println(result2)

  //prefix notation
  println(-myMember.value)
  println(!myMember)

  println(myMember isZero) // myMember.isZero

  println(myMember.apply())
  println(myMember())
}
