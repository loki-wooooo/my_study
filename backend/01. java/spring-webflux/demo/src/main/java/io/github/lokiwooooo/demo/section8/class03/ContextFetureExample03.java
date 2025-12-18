package io.github.lokiwooooo.demo.section8.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - Context는 체인의 맨 아래서부터 위로 전파한다.
 *      - 동일한 키 대해서 Write 할 경우, 해당 키에 대한 값을 덮어 쓴다.
 * */
@Slf4j
public class ContextFetureExample03 {
    public static void main(String[] args) throws InterruptedException{
        String key1 = "id";

        // 아래서 위로 올라가기 때문에 마지막 값인 "lokiwooooo11" 출력함
        Mono.deferContextual(contextView -> Mono.just("id: " + contextView.get(key1)))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "lokiwooooo11"))
                .contextWrite(context -> context.put(key1, "lokiwooooo22"))
                .subscribe(data -> log.info("onNext: {}", data));
        Thread.sleep(100L);
    }
}
