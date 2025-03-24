package com.group.libraryapp.calculator


class Calculator(
    // kotlin 공색 변수 컨벤션
//    private var _number: Int
    var number: Int
) {

//    val number: Int
//        get() = this._number

    fun add(operand: Int) {
//        this._number += operand
        this.number += operand
    }

    fun minus(operand: Int) {
//        this._number -= operand
        this.number -= operand
    }

    fun multiply(operand: Int) {
//        this._number *= operand
        this.number *= operand
    }

    fun divide(operand: Int) {
        if (operand == 0) {
            throw IllegalArgumentException("Division by zero")
        }
//        this._number /= operand
        this.number /= operand
    }
}