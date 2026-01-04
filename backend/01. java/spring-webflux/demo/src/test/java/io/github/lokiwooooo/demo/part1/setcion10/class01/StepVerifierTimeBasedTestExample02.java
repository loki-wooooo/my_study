package io.github.lokiwooooo.demo.part1.setcion10.class01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/*
 * 실제 시간을 가상 사건으로 대체하는 테스트 예제
 *  - thenAwait(Duration)을 통해 독점 시간만큼 가상으로 기다린다.
 *  - 즉, 특정 시간을 기다린 것처럼 시간을 당긴다.
 * */
public class StepVerifierTimeBasedTestExample02 {

    @Test
    public void getCODIV19CountTest() {
        StepVerifier
                // withVirtualTime > 가상시간의 범위안에서 실행됨
                .withVirtualTime(() -> TimeBasedExample.getCODIV19Count(
                        Flux.interval(Duration.ofHours(12)).take(1)
                ))
                .expectSubscription()
                .thenAwait(Duration.ofHours(12))
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }

}
