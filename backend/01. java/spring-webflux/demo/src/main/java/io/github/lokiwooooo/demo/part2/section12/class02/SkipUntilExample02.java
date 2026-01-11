package io.github.lokiwooooo.demo.part2.section12.class02;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * skipuntil 기본 예제
 * - 파라미터로부터 입력되는 predicate true가 될때까지 건너뛴다.
 *
 */
@Slf4j
public class SkipUntilExample02 {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .skipUntil(tuple -> tuple.getT2() > 10_000_000)
                .subscribe(data -> log.info("next: {}, {}", data.getT1(), data.getT2()))
        ;

    }
}
