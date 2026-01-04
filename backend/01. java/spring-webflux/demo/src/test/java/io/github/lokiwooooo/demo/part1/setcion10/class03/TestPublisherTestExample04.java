package io.github.lokiwooooo.demo.part1.setcion10.class03;

import io.github.lokiwooooo.demo.part1.setcion10.class01.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * TestPublisher를 사용해서 서비스 로직의 메서드에 대한 Unit Test 를 실시하는 예제
 * - Reactive Streams 사양을 위반해도 Publisher가 정상 동작하게 함으로써 서비스 로직을 검증하는 예제
 *
 */
public class TestPublisherTestExample04 {

    @Test
    public void divideByTwoTest() {
        // TestPublisher.Violation.ALLOW_NULL - reactive streams 조건을 위반하는 조건을 허용함
//        TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.deviceByTwo(source.flux()))
                .expectSubscription()
                .then(() -> source.next(2, 4, 6, 8, null)) // 실제 Null 값도 emit 처리
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectComplete() // null pointer exception 나옴
                .verify();
    }
}
