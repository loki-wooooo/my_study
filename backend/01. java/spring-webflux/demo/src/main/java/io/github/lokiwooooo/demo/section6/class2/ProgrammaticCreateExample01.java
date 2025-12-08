package io.github.lokiwooooo.demo.section6.class2;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/*
* create() Operator를 사용하는 예제
* - 일반적으로 Publisher의 데이터 생성을 단일 쓰레드에서 진행한다. 멀티 쓰레드에서도 가능함
* - 데이터 emit을 create Operator 내부에서 가능
* - Backpressure 적용 가능
* */
@Slf4j
public class ProgrammaticCreateExample01 {
    public static void main(String[] args) throws Exception {
        int task = 6;
        Flux
                .create((FluxSink<String> sink) ->
                    //FluxSink 데이터를 동기/비동기적으로 emit 처
                    IntStream // 일반적으로 단일 스레드로 사용함
                            .range(1, task)
                            .forEach(n -> sink.next(doTask(n)))
                )
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(n -> log.info("# create(): {}", n))
                .publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(n -> log.info("# map(): {}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }

    private static String doTask(int n) {
        // now tasking
        // complate to task
        return "task " + n + " result";
    }
}
