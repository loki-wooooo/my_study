package io.github.lokiwooooo.demo.part1.section7.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
* Operator 체인에서 publishOn()이 호출되면, publishOn() 호출 이후의 Operator 체인은
* 다음 publishOn()을 만나기 전까 publishOn()에서 지정한 Thread에서 실행이 된다.
* */
@Slf4j
public class SchedulerOperatorExample03 {
    public static void main(String[] args) throws InterruptedException{
        //main에서 실행하기 떄문에 publishOn, subscribeOn을 호출하지 않음
        Flux.fromArray(new Integer[]{1,3,5,7})
                .doOnNext(data -> log.info("fromArray: {}", data))
                .publishOn(reactor.core.scheduler.Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("filter: {}", data))
                .publishOn(reactor.core.scheduler.Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("map: {}", data))
                .subscribe(data -> log.info("data: {}", data));

        Thread.sleep(500L);
    }
}
