package io.github.lokiwooooo.demo.part2.section13.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * flatMap 기본 예제
 * - 들어오는 데이터들의 Publisher(Mono 또는 Flux)로 감싸저 있는 Publisher들을 벗겨내고 하나의 Publisher로 평면화 한다.
 *
 */
@Slf4j
public class FlatMapExample05 {

    public static void main(String[] args) {
        Flux
                .just("Good", "Bad")
                .map(word -> Mono.just(word)) // downstream에 전달은 mono type을 전달함
                .flatMap(word -> word)
                .subscribe(data -> log.info("data: {}", data));
    }

}
