package com.group.libraryapp.calculator

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
}

class CalculatorTest {

    /**
     * data class로 만들어서 체크
     *  // 데이터를 보관하기 위한 목적으로 설계된 특별한 종류의 클래스 (data class)
     *
     * 변수를 public로 변경해서 확인
     * */
    fun addTest() {

        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        // class 변수가 public 용
        if (calculator.number != 8) {
            throw IllegalArgumentException()
        }

        // dataclass 용
//        val expectedCalculator = Calculator(8)
//        if (calculator != expectedCalculator){
//            throw IllegalArgumentException()
//        }
    }

    fun minusTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.minus(3)

        // then
        if (calculator.number != 2) {
            throw IllegalArgumentException()
        }
    }

    fun multiplyTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.multiply(3)

        // then
        if (calculator.number != 15) {
            throw IllegalArgumentException()
        }
    }

    fun divideTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(3)

        // then
        if (calculator.number != 2) {
            throw IllegalArgumentException()
        }
    }

    fun divideExceptionTest() {
        //given
        val calculator = Calculator(5)

        //when
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            if (e.message != "Division by zero") {
                throw IllegalStateException("메시지가 다릅니다.")
            }
            // 테스트 성공!
            return
        } catch (e: Exception) {
            throw IllegalStateException()
        }

        //then
        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")
    }


}