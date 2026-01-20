package io.github.lokiwooooo.demo.part2.section13.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * flatMapSequential 기본 개념 예제
 * - 비동기적으로 동작할 경우에도 emit 되는 순서를 보장한다.
 * */
@Slf4j
public class FlatMapSequentialExample {
    public static void main(String[] args) throws InterruptedException{
        Flux
                .range(2, 8)
                .flatMapSequential(dan -> Flux
                        .range(1, 9)
                        .publishOn(Schedulers.parallel()) // 비동기적 처리를 실행하면, emit시 순서보장을 하지 않음
                        .map(num -> dan * num))
                .subscribe(data -> log.info("data: {}", data));

        Thread.sleep(200L);
    }
}
