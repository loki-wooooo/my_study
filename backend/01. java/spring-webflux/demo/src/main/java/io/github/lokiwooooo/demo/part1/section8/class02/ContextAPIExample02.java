package io.github.lokiwooooo.demo.part1.section8.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/*
 * Context API 예제 코드
 * - putall(contextView) API 사용
 * */
@Slf4j
public class ContextAPIExample02 {
    public static void main(String[] args) throws InterruptedException {
        String key1 = "id";
        String key2 = "name";
        String key3 = "country";

        // putAll은 “읽기 전용 컨텍스트 뷰(ContextView)”를 받아서 합치는 메서드라서, 우리가 만든 Context를 readOnly()로 ContextView로 바꾼 다음 putAll에 넘긴다.
        Mono.deferContextual(contextView -> Mono.just("id: " + contextView.get(key1) + ", name: " + contextView.get(key2) + ", country: " + contextView.get(key3)))
                .publishOn(Schedulers.parallel())
                // readOnly contextView에 일치해야하기 떄문에 contextView객체를 리턴받아서 putall에 파라미터를 전달
                .contextWrite(context -> context.putAll(Context.of(key2, "hswoo", key3, "korea").readOnly()))
                .contextWrite(context -> context.put(key1, "lokiwooooo"))
                .subscribe(data -> log.info("onNext: {}", data));

        Thread.sleep(100L);

    }
}
