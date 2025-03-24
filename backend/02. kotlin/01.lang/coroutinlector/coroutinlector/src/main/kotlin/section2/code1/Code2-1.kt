package info.coroutin.section2.code1

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit>(CoroutineName("runBlocking 코루틴")) {
    println("[${Thread.currentThread().name}] runBlocking 코루틴 시작")

    launch(CoroutineName("launch 코루틴")) {
        println("[${Thread.currentThread().name}] launch 코루틴 시작")
    }
}