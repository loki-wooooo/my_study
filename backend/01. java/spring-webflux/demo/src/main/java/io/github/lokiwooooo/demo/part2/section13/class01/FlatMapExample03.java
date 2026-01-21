package io.github.lokiwooooo.demo.part2.section13.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * FlatMap 기본 예제
 * - 비동기적으로 동작할 경우, emit 되는 순서를 보장하지 않는다.
 *
 */
@Slf4j
public class FlatMapExample03 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(2, 8)
                .flatMap(dan -> Flux
                        .range(1, 9)
                        .publishOn(Schedulers.parallel()) // 비동기 처리
                        .map(n -> dan + " * " + n + " = " + dan * n))
                .subscribe(data -> log.info("{}", data))
        ;

        Thread.sleep(200L);
    }
}
