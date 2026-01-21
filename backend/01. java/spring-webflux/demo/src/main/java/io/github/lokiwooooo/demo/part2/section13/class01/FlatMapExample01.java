package io.github.lokiwooooo.demo.part2.section13.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * flatMap 기본 예제
 * - inner publisher(sequence)를 통해 1대다의 데이터 매핑이 가능하다.
 *
 */
@Slf4j
public class FlatMapExample01 {

    public static void main(String[] args) {
        Flux
                .just("Good", "Bad")
                .flatMap(feeling ->
                        Flux.just("Morning", "Afternoon", "Evening").map(time -> feeling + " " + time)
                )
                .subscribe(data -> log.info("data: {}", data));
    }

}
