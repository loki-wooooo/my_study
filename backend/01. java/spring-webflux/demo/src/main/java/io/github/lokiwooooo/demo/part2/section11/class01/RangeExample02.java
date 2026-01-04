package io.github.lokiwooooo.demo.part2.section11.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * range()의 사용 예제
 * - 명령형 방식의 for문을 대체하는 예제
 */
@Slf4j
public class RangeExample02 {
    public static void main(String[] args) {
        List<String> coinNames = SampleData.coinNames;

        Flux
                .range(0, coinNames.size())
                .subscribe(idx -> log.info("data: {}", coinNames.get(idx)));
    }
}
