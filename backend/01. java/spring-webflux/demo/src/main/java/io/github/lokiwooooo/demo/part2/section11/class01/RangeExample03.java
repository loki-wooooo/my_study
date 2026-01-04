package io.github.lokiwooooo.demo.part2.section11.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * range()의 사용 예제
 * - range()를 사용해서, list의 특정 idx에 해당하는 데이터를 조회하는 예제
 */
@Slf4j
public class RangeExample03 {
    public static void main(String[] args) {
        Flux
                .range(7, 5)
                .map(idx -> SampleData.btcTopPricesPerYear.get(idx))
                .subscribe(tuple -> log.info("{}'s {}", tuple.getT1(), tuple.getT2()));
    }
}
