package io.github.lokiwooooo.demo.part1.section8.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/*
 * Context API 중에서 write API 예제 코드
 * - context.of(...) 사용
 * */
@Slf4j
public class ContextAPIExample01 {
    public static void main(String[] args) throws InterruptedException{
        String key1 = "id";
        String key2 = "name";
        Mono<String> mono = Mono.deferContextual(
                        contextView -> Mono.just("ID: " + contextView.get(key1) + ", " + "NAME: " + contextView.get(key2))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(Context.of(key1, "lokiwooooo", key2, "hswoo"));

        mono.subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);

    }
}
