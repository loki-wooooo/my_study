package info.coroutin.section3.code1

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

// 개발자가 잘 모르는거 기준으로 실행하면, 오희려 비효율 적일수도 있음.
val multiThreadDispatcher: CoroutineDispatcher = newFixedThreadPoolContext(2, "multiThread")

fun main() = runBlocking<Unit> {
    launch(multiThreadDispatcher) {
        println("[${Thread.currentThread().name}] 실행1")
    }

    launch(multiThreadDispatcher) {
        println("[${Thread.currentThread().name}] 실행2")
    }
}