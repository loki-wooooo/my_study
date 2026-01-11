package io.github.lokiwooooo.demo.part2.section13.class00;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * map 기본 예제
 * - Upstream에서 emit 된 데이터를 변환한 후, Downstream으로 emit 한다. *
 */
@Slf4j
public class MapExample01 {
    public static void main(String[] args) {
        Flux
                .just("Green-Circle", "Yellow-Circle", "Blue-Circle")
                .map(circle -> circle.replace("Circle", "Rectangle"))
                .subscribe(data -> log.info("data: {}", data));
    }
}
