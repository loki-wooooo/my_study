package io.github.lokiwooooo.demo.part2.section13.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * FlatMap 기본 예제
 * - 구구단 3단 출력 예제
 *
 */
@Slf4j
public class FlatMapExample02 {
    public static void main(String[] args) {
        Flux
                .just(3)
                .flatMap(dan -> Flux.range(1, 9).map(n -> dan + " * " + n + " = " + dan * n))
                .subscribe(data -> log.info("{}", data))
        ;
    }
}
