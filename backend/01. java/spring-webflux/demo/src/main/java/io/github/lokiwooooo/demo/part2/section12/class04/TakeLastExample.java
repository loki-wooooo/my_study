package io.github.lokiwooooo.demo.part2.section12.class04;

import io.github.lokiwooooo.demo.part2.section11.class01.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * TakeLast 기본 예제
 * - emit 된 데이터 중에서 파라미터로 입력된 갯수만큼 가장 마지막에 emit된 데이터만 emit 한다.
 *
 *
 */
@Slf4j
public class TakeLastExample {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .takeLast(2)
                .subscribe(data -> log.info("data: {}", data));

    }
}
