package io.github.lokiwooooo.demo.part2.section12.class00;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Filter 기본 예제
 * */
@Slf4j
public class FilterExample01 {
    public static void main(String[] args) {
        Flux
                .range(1, 20)
                .filter(num -> num % 2 == 0)
                .subscribe(data -> log.info("data: {}", data));
    }
}
