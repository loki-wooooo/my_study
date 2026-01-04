package io.github.lokiwooooo.demo.part1.setcion10.class03;

import io.github.lokiwooooo.demo.part1.setcion10.class01.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * TestPublisher를 사용해서 서비스 로직의 메서드에 대한 Unit Test 를 실시하는 예제
 * - 정상 동작하는 TestPublisher
 * - next() 사용
 * - 에러 발생 여부 검증
 *
 */
public class TestPublisherTestExample02 {

    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();
        StepVerifier
                .create(GeneralExample.occurError(source.flux())) // source.flux() TestPublisher를 사용
                .expectSubscription()
                .then(() -> source.next(2, 4, 6, 8, 10)) // data emit 처
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectError() // 마지막 데이터는 에러를 기대함
                .verify();
    }
}
