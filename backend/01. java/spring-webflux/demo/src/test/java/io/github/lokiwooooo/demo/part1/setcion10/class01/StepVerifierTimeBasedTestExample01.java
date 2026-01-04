package io.github.lokiwooooo.demo.part1.setcion10.class01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

/*
 * 실제 시간을 가상 사건으로 대체하는 테스트 예제
 *   - 특정 시간만큼 시간을 당긴다.
 * */
public class StepVerifierTimeBasedTestExample01 {

    @Test
    public void getCODIV19CountTest() {
        StepVerifier
                // withVirtualTime > 가상시간의 범위안에서 실행됨
                .withVirtualTime(() -> TimeBasedExample.getCODIV19Count(
                        Flux.interval(Duration.ofHours(12)).take(1)
                ))
                .expectSubscription()
                // 후속작업 실행
                // advanceTimeBy-> 시간을 앞당겨줌
                .then(() -> VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(12)))
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }

}
