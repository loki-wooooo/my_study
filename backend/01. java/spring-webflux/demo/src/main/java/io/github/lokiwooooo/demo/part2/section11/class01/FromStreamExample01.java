package io.github.lokiwooooo.demo.part2.section11.class01;

import reactor.core.publisher.Flux;

/**
 * fromStream()의 사용 예제
 * - Stream을 파라미터로 입력 받아 Stream에 포함된 데이터를 차례대로 emit 한다.
 *
 */
public class FromStreamExample01 {
    public static void main(String[] args) {
        Flux
                .fromStream(SampleData.coinNames.stream())
                .subscribe(data -> System.out.println("data: " + data));
    }
}
