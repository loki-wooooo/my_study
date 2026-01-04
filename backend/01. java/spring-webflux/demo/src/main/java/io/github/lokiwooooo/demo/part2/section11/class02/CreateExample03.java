package io.github.lokiwooooo.demo.part2.section11.class02;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * create 개념 이해 예제
 *  - Subscriber가 request 할 경우에 next signal 이벤트를 발생하는 예제
 *  - Backpressure 전략을 적용하는 예제
 */
@Slf4j
public class CreateExample03 {
    public static int start = 1;
    public static int end = 4 ;

    public static void main(String[] args) throws InterruptedException {
        Flux.create((FluxSink<Integer> emitter) -> {
                    emitter.onRequest(n -> { // n은 처리가능 개수, 밑에서 지정함 prefetch의 수
                        log.info("# requested: " + n);
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        for (int i = start; i <= end; i++) { // 4건의 데이터 emit
                            emitter.next(i);
                        }
                        start += 4;
                        end += 4;
                    });

                    emitter.onDispose(() -> {
                        log.info("# clean up");
                    });
                }, FluxSink.OverflowStrategy.DROP) // 2건 초과해서 emit되는 경우 drop함
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel(), 2) // 2는 스레드에서 한번에 2건 처리하겠다는 의미
                .subscribe(data -> { // 결과적으로 2건은 정상처리되고 2건은 drop됨
                    log.info(data.toString());
                });

        Thread.sleep(3000L);
    }
}