package lecture

import scala.jdk.Accumulator

object Recursion extends App {

    def factorialIterative(n: Int): BigInt = {
        var result = BigInt(1)
        for (i <- 2 to n) {
            result *= i
        }
        result
    }

    println(factorialIterative(3))

    def factorialStackRecursive(n: Int): Int = {
        if (n <= 1) 1
        else n * factorialStackRecursive(n - 1)
    }

    println(factorialStackRecursive(3))

    /**
     * accumulator -> 중간 계산 결과를 저장하는 데 사용되는 변수
     * @tailrec -> tail Recursive가 되는지 안되는지 확인
     * */
    def factorialTailRecursive(n: Int, accumulator: Int = 1): Int = {
        if (n <= 1) 1
        else factorialTailRecursive(n - 1, n * accumulator)
    }

    println(factorialTailRecursive(3))
}
