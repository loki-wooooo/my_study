package io.github.lokiwooooo.demo.part1.class02;

import io.github.lokiwooooo.demo.util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*
 * unbounded request 일 경우, Downstream 에 Backpressure Latest 전략을 적용하는 예제
 *   - Downstream 으로 전달 할 데이터가 버퍼에 가득 찰 경우,
 *     버퍼 밖에서 폐기되지 않고, 대기하는 가장 나중(최근) Emit된 데이터부터 버퍼에 채우는 전략
 *   - emit된 데이터를 확인하지 않고, 추측이 가능함
 * */
public class BackpressureStrategyLatestExample {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel()) // thread 추가해서 작업을 할당해서 어떤 작업을 수행하도록 하는 함수 -> schedulers
                .subscribe(data -> {
                                TimeUtil.sleep(5L);
                                System.out.println("Data : " + data);
                        },
                        error -> System.out.println("Error : " + error.getMessage())
                );

        TimeUtil.sleep(2000L);
    }
}
