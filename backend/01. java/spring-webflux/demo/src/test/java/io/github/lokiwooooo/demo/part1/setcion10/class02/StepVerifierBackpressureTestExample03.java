package io.github.lokiwooooo.demo.part1.setcion10.class02;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/*
 * Backpressure DROP 전략을 검증하는 예제
 * - expectError()를 사용하 에러가 발생되었는지 검증
 * - verifyThenAssertThat()을 사용하 검증 이후에 assertion method를 사용하여 추가 검증을 할 수 있다.
 * - hasDiscardedElement()를 사용하여 discard된 데이터가 있는지 검증한다. Backpressure DROP 전략은 Drop된 데이터가 discard된다.
 * - hasDiscarded()를 사용하여 discard된 데이가 무었인지 검증한다. Backpressure DROP 전략은 Drop된 데이터가 discard된다.
 * */
public class StepVerifierBackpressureTestExample03 {

    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByDropStrategy(), 1L)
                .thenConsumeWhile(num -> num >= 1) //emit한 데이터를 소비함
                .expectComplete() // drop되어 폐기되기 때문에 Sequence는 정상적으로 끝남
                .verifyThenAssertThat()
                .hasDiscardedElements() // 폐기된 데이터가 있으면 성공
                .hasDiscarded(2, 3, 4, 5, 6, 99, 100); // 폐기된 데이터의 검증
        //.hasDropped(2,3,4,5,6,99,100); // backpressure의 전략과는 다름
    }
}
