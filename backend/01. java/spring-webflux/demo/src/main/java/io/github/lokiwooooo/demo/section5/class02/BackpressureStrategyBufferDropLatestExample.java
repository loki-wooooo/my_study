package io.github.lokiwooooo.demo.section5.class02;

import io.github.lokiwooooo.demo.util.TimeUtil;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*
 * unbounded request 일 경우, Downstream 에 Backpressure Buffer DROP_LATEST 전략을 적용하는 예제
 *   - Downstream 으로 전달 할 데이터가 버퍼에 가득 찰 경우, 버퍼 안에 있는 데이터 중에서 가장 최신(나중) 버퍼에 들어온 데이터부터 Drop 시키는 전략
 * */
public class BackpressureStrategyBufferDropLatestExample {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(300L))
                .doOnNext(System.out::println)
                .onBackpressureBuffer(2,
                        dropped -> System.out.println("dropped data : " + dropped),
                        BufferOverflowStrategy.DROP_LATEST)
                .doOnNext(System.out::println)
                .publishOn(Schedulers.parallel(), false, 1) // thread 추가해서 작업을 할당해서 어떤 작업을 수행하도록 하는 함수 -> schedulers
                .subscribe(data -> {
                                TimeUtil.sleep(5L);
                                System.out.println("Data : " + data);
                        },
                        error -> System.out.println("Error : " + error.getMessage())
                );

        TimeUtil.sleep(2000L);
    }
}
