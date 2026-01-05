package io.github.lokiwooooo.demo.part2.section12.class01;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * skip 기본 개념 예제
 * - 년도별 BTC 최고가 중에서 2000만원 이상이였던 년도 중, 오래된 년도의 2년을 건너뛴 가격을 표시한다.
 *
 */
@Slf4j
public class SkipExample03 {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .filter(tuple -> tuple.getT2() > 20_000_000)
                .skip(2)
                .subscribe(data -> log.info("next: {}, {}", data.getT1(), data.getT2()));

    }
}
