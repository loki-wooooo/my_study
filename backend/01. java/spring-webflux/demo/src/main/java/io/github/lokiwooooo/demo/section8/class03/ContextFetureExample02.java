package io.github.lokiwooooo.demo.section8.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - Context는 체인의 맨 아래서부터 위로 전파한다.
 *      - 따라서 Operator 체인에서 Context read 읽는 동작이 Context write 동작 밑에 있을 경우에는 write한 값을 read 할 수 없다.
 * */
@Slf4j
public class ContextFetureExample02 {
    public static void main(String[] args) throws InterruptedException{
        String key1 = "id";
        String key2 = "name";

        Mono.deferContextual(contextView -> Mono.just("id: " + contextView.get(key1)))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key2, "hswoo"))

                // Context 아래서 위로 -> key2의 해당하는 값이 존재하지 않기 떄문에 "tom"을 출력
                .transformDeferredContextual((mono, contextView) ->
                        mono.map(data -> data + ", " + contextView.getOrDefault(key2, "tom")))
                .contextWrite(context -> context.put(key1, "lokiwooooo"))
                .subscribe(data -> log.info("onNext: {}", data));
        Thread.sleep(100L);
    }
}
