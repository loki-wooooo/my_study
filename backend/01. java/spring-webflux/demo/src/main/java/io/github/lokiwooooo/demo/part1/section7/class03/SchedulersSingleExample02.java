package io.github.lokiwooooo.demo.section7.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Schedulers.single()을 적용할 경우,
 * 첫번째 Schedulers.single()에서 할당괸 쓰레드를 재사용 한다.
 * => 호출마다 매번 새뢰운 쓰레드 하나를 생성한다.
 */
@Slf4j
public class SchedulersSingleExample02 {
    public static void main(String[] args) throws InterruptedException {
        doTask("task1")
                .subscribe(data -> log.info("subscribe1: {}", data));

        doTask("task2")
                .subscribe(data -> log.info("subscribe2: {}", data));

        Thread.sleep(200L);
    }

    private static Flux<Integer> doTask(final String taskName) {
        return Flux.fromArray(new Integer[]{1, 3, 5, 7})
                // 쓰레드 이름 지정 및 데몬스레스 사용 유/무
                .publishOn(reactor.core.scheduler.Schedulers.newSingle("new-signle", true))
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("{}: {}", taskName, data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("{}: {}", taskName, data));
    }
}
