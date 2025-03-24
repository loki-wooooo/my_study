package info.coroutin.section1.code1

import kotlin.concurrent.thread

fun main(){
    println("[${Thread.currentThread().name }}] 시작")
    Thread.sleep(1000L)
    println("[${Thread.currentThread().name }}] 종료")
}