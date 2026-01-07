package io.github.lokiwooooo.demo.part2.section12.class00;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * 비트코인의 연간 최고가격이 1000만원을 넘는 연도와 가격을 필터링하는 예제
 *
 */
@Slf4j
public class FilterExample02 {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .filter(tuple -> tuple.getT2() > 10_000_000)
                .subscribe(filtered -> log.info("{} : {}", filtered.getT1(), filtered.getT2()));
    }
}
