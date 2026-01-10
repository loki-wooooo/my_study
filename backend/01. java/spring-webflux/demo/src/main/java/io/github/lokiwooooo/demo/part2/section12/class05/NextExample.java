package io.github.lokiwooooo.demo.part2.section12.class05;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * next 기본 예제
 * - emit 된 데이터 중에서 첫번째 데이터만 DownStream 으로 emit 한다.
 *
 */
@Slf4j
public class NextExample {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .doOnNext(data -> log.info("onNext: {}", data))
                .filter(tuple -> tuple.getT1() == 2015)
                .next() //upstream에서 있는 데이터 '1'개만 downstream 처리함
                .subscribe(data -> log.info("data: {}", data));

    }
}
