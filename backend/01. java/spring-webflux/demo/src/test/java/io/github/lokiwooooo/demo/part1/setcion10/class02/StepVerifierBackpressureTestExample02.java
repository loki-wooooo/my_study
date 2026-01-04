package io.github.lokiwooooo.demo.part1.setcion10.class02;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/*
 * Backpressure ERROR 전략을 검증하는 예제
 * - expectError()를 사용하 에러가 발생되었는지 검증
 * - verifyThenAssertThat()을 사용하 검증 이후에 assertion method를 사용하여 추가 검증을 할 수 있다.
 * - hasDiscardedElement()를 사용하여 discard된 데이터가 있는지 검증한다. OverflowException이 발생할때, 2가 discard된다.
 * - hasDiscarded()를 사용하여 discard된 데이가 무었인지 검증한다. OverflowException이 발생할때, 2가 discard된다.
 * - hasDroppedElement()를 사용하여, Hooks.onNextDropped()으로 Drop된 데이터가 있는지 검증한다.
 * - hasDropped()를 사용하여 Hooks.onNextDropped()으로 Drop된 데이터가 무엇인지 검증한다.
 * */
public class StepVerifierBackpressureTestExample02 {

    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L)
                .thenConsumeWhile(num -> num >= 1) //emit한 데이터를 소비함
                .expectError()
                .verifyThenAssertThat()
                .hasDiscardedElements() // 폐기된 데이터가 있으면 성공
                .hasDiscarded(2) // 폐기된 데이터의 검증
                .hasDroppedElements() // drop된 데이터 있으면 성성
                .hasDropped(3, 4, 5, 6, 98, 99, 100); // drop된 데이터 검증
    }
}
