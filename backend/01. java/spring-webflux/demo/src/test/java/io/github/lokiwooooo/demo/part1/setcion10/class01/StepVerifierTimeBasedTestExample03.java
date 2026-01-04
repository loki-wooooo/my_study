package io.github.lokiwooooo.demo.part1.setcion10.class01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/*
 * 검증에 소요되는 시간을 제한하는 예제
 *  - verify(duration)을 통해 설정한 시간내에 검증이 끝나는지를 확인 할 수 있다.
 *
 * */
public class StepVerifierTimeBasedTestExample03 {

    @Test
    public void getCODIV19CountTest() {
        StepVerifier
                .create(
                        TimeBasedExample.getCODIV19Count(
                                Flux.interval(Duration.ofMinutes(1)).take(1)
                        )
                )
                .expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                // 시간제한을 두어 모든 기능이 실행되는지 확인
                .verify(Duration.ofSeconds(3)); //timeout으로 실패처리
    }

}
