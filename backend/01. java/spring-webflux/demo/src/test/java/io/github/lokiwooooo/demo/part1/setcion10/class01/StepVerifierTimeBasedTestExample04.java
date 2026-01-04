package io.github.lokiwooooo.demo.part1.setcion10.class01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuples;

import java.time.Duration;

/*
 * expectNoEvent(Duration)으로 지정된 대기 시간동안 이벤트가 없음을 확인하는 예제
 * */
public class StepVerifierTimeBasedTestExample04 {

    @Test
    public void getCODIV19CountTest() {
        StepVerifier
                // Flux.interval(Duration.ofMinutes(1))은 구독하자마자 데이터를 던지는 것이 아니라, 선 설정된 시간(1분)이 지나야 첫 번째 데이터(0)를 보냅
                .withVirtualTime(() -> TimeBasedExample.getVoteCount(Flux.interval(Duration.ofMinutes(1))))
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNext(Tuples.of("중구", 15400))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }

}
