package io.github.lokiwooooo.demo.part1.setcion10.class03;

import io.github.lokiwooooo.demo.part1.setcion10.class01.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * TestPublisher를 사용해서 서비스 로직의 메서드에 대한 Unit Test 를 실시하는 예제
 * - 정상 동작하는 TestPublisher
 * - emit() 사용
 *
 */
public class TestPublisherTestExample03 {

    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();
        StepVerifier
                .create(GeneralExample.takeNumber(source.flux(), 3L)) // source.flux() TestPublisher를 사용
                .expectSubscription()
                .then(() -> source.emit(1, 2, 3, 4, 5)) // data emit 처
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }
}
