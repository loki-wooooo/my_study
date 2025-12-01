package io.github.lokiwooooo.demo.section3.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
* Flux 기본 예제
* */
@Slf4j
public class FluxExample01 {
    public static void main(String[] args) {
        Flux.just(6,9,13)
                .map(num -> num % 2)
                .subscribe(remainder -> log.info("remainder: {}", remainder));

    }
}
