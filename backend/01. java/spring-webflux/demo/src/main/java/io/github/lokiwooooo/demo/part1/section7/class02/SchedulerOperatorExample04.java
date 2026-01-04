package io.github.lokiwooooo.demo.part1.section7.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
* subscribeOn()은 구독 직후에 실행 될 쓰레드를 지정한다.
* 즉, 원본 Publisher의 실행 쓰래드를 subscribeOn()에서 지정한 쓰레드로 바꾼다.
* */
@Slf4j
public class SchedulerOperatorExample04 {
    public static void main(String[] args) throws InterruptedException{
        //main에서 실행하기 떄문에 publishOn, subscribeOn을 호출하지 않음
        Flux.fromArray(new Integer[]{1,3,5,7}) // main -> 다른 thread도 변경함 (최상위 upstream에서도 다른 thread를 사용)
                .subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic())
                .doOnNext(data -> log.info("fromArray: {}", data))
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("filter: {}", data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("map: {}", data))
                .subscribe(data -> log.info("data: {}", data));

        Thread.sleep(500L);
    }
}
