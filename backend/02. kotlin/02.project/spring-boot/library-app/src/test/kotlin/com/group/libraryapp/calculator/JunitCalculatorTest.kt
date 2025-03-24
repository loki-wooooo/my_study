package com.group.libraryapp.calculator

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculatorTest {

    @Test
    fun addTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        // assertThat -> 확인하고 싶은값
        // isEqualTo -> 기대값
        assertThat(calculator.number).isEqualTo(8)

    }

    @Test
    fun minusTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.minus(3)

        //then
        // assertThat -> 확인하고 싶은값
        // isEqualTo -> 기대값
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun multiplyTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.multiply(3)

        //then
        // assertThat -> 확인하고 싶은값
        // isEqualTo -> 기대값
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun divideTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.divide(2)

        //then
        // assertThat -> 확인하고 싶은값
        // isEqualTo -> 기대값
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun divideExceptionTest() {
        // given
        val calculator = Calculator(5)

        // when & then
        //에러가 난 TEST Message를 갖고와서 비교함
//        val message = assertThrows<IllegalArgumentException> {
//            calculator.divide(0)
//        }.message
//        assertThat(message).isEqualTo("Division by zero")
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("Division by zero")
        }

    }

}