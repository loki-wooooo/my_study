package io.github.lokiwooooo.demo.part2.section12.class04;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * takeutil 기본 예제
 * - 파라미터로 입력되는 Predicate이 true가 될때까지 데이터를 emit 한다.
 * - emit 된 데이터에는 Predicate이 true로 matching 되는 데이터가 포함된다.
 *
 */
@Slf4j
public class TakeUntilExample {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .takeUntil(tuple -> tuple.getT2() > 10_000_000)
                .subscribe(data -> log.info("data: {}", data));
    }
}
