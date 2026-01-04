package io.github.lokiwooooo.demo.part2.section11.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Defer 사용 예제
 * - 실제로 구독이 발생하는 시점에 데이터를 emit 하는 예제
 */
@Slf4j
public class DeferExample01 {
    public static void main(String[] args) throws InterruptedException {
        log.info("# Starting");

        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        //just를 defer로 한번더 wrapper 처리
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000L);

        // just operator를 선언한 시점에 data emit
        justMono.subscribe(data -> log.info("justMono1: {}", data));
        // 구독이 발생하는 시점에 데이터를 emit
        deferMono.subscribe(data -> log.info("deferMono1: {}", data));

        Thread.sleep(2000L);

        justMono.subscribe(data -> log.info("justMono2: {}", data));
        deferMono.subscribe(data -> log.info("deferMono2: {}", data));


    }
}
