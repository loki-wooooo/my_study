package io.github.lokiwooooo.demo.part2.section11.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * fromIterable() 의 사용 예제
 * - Iterable의 구현 클래스를 파라미터로 입력받아 차례대로 emit 한다.
 *
 */
@Slf4j
public class FomIterableExample01 {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coinNames)
                .subscribe(data -> log.info("data: {}", data));
    }
}
