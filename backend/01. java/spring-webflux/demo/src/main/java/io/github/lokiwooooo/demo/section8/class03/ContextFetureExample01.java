package io.github.lokiwooooo.demo.section8.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - Context는 각각의 구독을 통해 Reactor Sequence에 연결 되며, 체인의 각 연산자가 연결된 Context에 접근할 수 있어야 한다.
 * */
@Slf4j
public class ContextFetureExample01 {
    public static void main(String[] args) throws InterruptedException{
        String key1 = "id";

        Mono<String> mono = Mono.deferContextual(contextView -> Mono.just("id: " + contextView.get(key1)))
                .publishOn(Schedulers.parallel());

        // 구독이 될 때마다 context가 연결됨
        mono.contextWrite(context -> context.put(key1, "lokiwooooo"))
                .subscribe(data -> log.info("onNext: {}", data));

        mono.contextWrite(context -> context.put(key1, "lokiwooooo2"))
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);



    }
}
