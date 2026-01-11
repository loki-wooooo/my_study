package io.github.lokiwooooo.demo.part2.section13.class00;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * map 활용예제
 * - 2021년도의 비트코인 최고가 시점의 수익률을 계산하는 예제
 *
 */
@Slf4j
public class MapExample02 {
    public static void main(String[] args) {
        final double buyPrice = 40_000_000;
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .filter(tuple -> tuple.getT1() == 2021)
                .doOnNext(data -> log.info("data: {}", data))
                .map(tuple -> calculateProfitRate(buyPrice, tuple.getT2()))
                .subscribe(data -> log.info("result: {}%", data));
    }

    // 수익률 계산(내가산 가격/ 최고 가격)
    private static double calculateProfitRate(final double buyPrice, final double topPrice) {
        return (topPrice - buyPrice) / buyPrice * 100;
    }
}
