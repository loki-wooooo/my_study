package io.github.lokiwooooo.demo.section7.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Schedulers.immediate()를 적용하기 전,
 * 2개의 parallel 쓰레드가 할당된다.
 * */
@Slf4j
public class SchedulersImmediateExample01 {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .publishOn(reactor.core.scheduler.Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("publishOn1: {}", data))
                .publishOn(reactor.core.scheduler.Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("publishOn2: {}", data))
                .subscribe(data -> log.info("subscribe: {}", data));
        Thread.sleep(200L);
    }
}
