package io.github.lokiwooooo.demo.section8.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/*
 * Context 개념 설명 예제 코드
 * - contextWrite()으로 Context에 값을 쓸 수 있고, ContextView.get()을 통해서 Context에 저장된 값을 Read 할 수 있다.
 * - ContextView는 deferContextual() 또는 transformDeferredContextual()을 통해 제공된다.
 * */
@Slf4j
public class ContextIntroduceExample01 {
    public static void main(String[] args) throws InterruptedException {
        String key = "message";
        // context -> key,value 형태로 저장됨
        // deferContextual -> datasource 레벨
        // 서로 다른 thread에서도 context 내부의 상태값을 공유해서 사용함
        Mono<String> mono = Mono.deferContextual(contextView ->
                        Mono.just("Hello" + " " + contextView.get(key)).doOnNext(log::info)
                )
                .subscribeOn(Schedulers.boundedElastic()) //upstream이용한 데이터 Emit 추가지정
                .publishOn(Schedulers.parallel()) //downstream이용한 데이터 emit 추가지정
                .transformDeferredContextual((mono2, contextView) -> mono2.map(data -> data + " " + contextView.get(key)))
                .contextWrite(context -> context.put(key, "Reactor")); //

        mono.subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);
    }
}
