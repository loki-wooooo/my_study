package io.github.lokiwooooo.demo.part2.section12.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * take 기본 예제
 * - 파라미터로 입력한 숫자만큼만 DownStream으로 emit 한다.
 *
 *
 */
@Slf4j
public class TakeExample02 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofSeconds(1))
                .doOnNext(data -> log.info("data: {}", data))
                .take(Duration.ofSeconds(2))
                .subscribe(data -> log.info("subscrbe: {}", data));

        Thread.sleep(4000L);
    }
}
