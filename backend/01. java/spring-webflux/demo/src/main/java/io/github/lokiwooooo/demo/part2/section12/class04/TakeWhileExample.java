package io.github.lokiwooooo.demo.part2.section12.class04;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * takeWhile 기본 예제
 * - 파라미터로 입력되는 Predicate가 true인 동안 emit된 데이터만 Downstream에 emit 한다.
 *
 */
@Slf4j
public class TakeWhileExample {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .takeWhile(tuple -> tuple.getT2() < 10_000_000)
                .subscribe(data -> log.info("data: {}", data));
    }
}
