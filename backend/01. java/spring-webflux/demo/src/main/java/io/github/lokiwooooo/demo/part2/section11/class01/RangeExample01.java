package io.github.lokiwooooo.demo.part2.section11.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * range()의 사용 예제
 * - 첫번째 파라미터(start)부터 두번째 파라미터(count)만큼 1씩 증가한 연속된 정수를 emit한다.
 *
 */
@Slf4j
public class RangeExample01 {
    public static void main(String[] args) {
        Flux
                .range(5, 10)
                .subscribe(data -> log.info("data: {}", data));
    }
}
