package io.github.lokiwooooo.demo.section3.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * 여러개의 Flux를 연결해서 하나의 Flux로 결합하는 예제
 * */
@Slf4j
public class FluxExample04 {
    public static void main(String[] args) {
        //concat 각 퍼블리셔을 연결해주는 오퍼레이
        Flux.concat(
                Flux.just("Hello")
                , Flux.just("Reactor")
                , Mono.just("World")
        )
                .collectList() // 
                .subscribe(wordList -> log.info("data: {}", wordList));
    }
}
