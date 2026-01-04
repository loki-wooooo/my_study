package io.github.lokiwooooo.demo.part1.section8.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - inner sequence 내부에서는 외부 Context에 저장된 데이터를 읽을 수 있다.
 * - inner sequence 내부에서 Context에 저장된 데이터는 inner sequence 외부에서 읽을 수 없다.
 *
 */
@Slf4j
public class ContextFetureExample04 {
    public static void main(String[] args) throws InterruptedException {
        String key1 = "id";
        Mono.just("Kevin")
                // inner sequence값을 밖에서 못불러옴
                //.transformDeferredContextual((mono, outerContext) -> outerContext.get("job"))
                //flatMap안에 mono sequence operator를 정의할 수 있음 => inner sequence
                .flatMap(name -> Mono.deferContextual(contextView ->
                                Mono.just(name + " " + contextView.get(key1)) //name -> just에서 갖고온 값
                                        .transformDeferredContextual((mono, innerContext) ->
                                                mono.map(data -> data + ", " + innerContext.get("job"))
                                        )
                                        .contextWrite(context -> context.put("job", "hswoo"))
                        )
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "lokiwooooo"))
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);
    }
}
