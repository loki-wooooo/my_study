package io.github.lokiwooooo.demo.section3.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * 2개의 Mono를 연결해서 Flux로 변환하는 예제
 * */
@Slf4j
public class FluxExample03 {
    public static void main(String[] args) {
        //concatWith -> mono 끼리 합쳐서 새로운 Flux를 만들어줌 (앞과 뒤를 입력해줌)
        Flux<Object> flux = Mono.justOrEmpty(null)
                .concatWith(Mono.just("Hello Reactor"));
        flux.subscribe(data -> log.info("data: {}", data));
    }
}
