package io.github.lokiwooooo.demo.section9.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
 * 분리된 method 에서 checkpoint() Operator 를 이용한 예제
 * - 역할별로 별도의 메서드로 분리 처리
 * */
@Slf4j
public class CheckpointExample07 {
    public static void main(String[] args) {
        Flux<Integer> source = Flux.just(2,4,6,8);
        Flux<Integer> other = Flux.just(2,4,6,0);

        Flux<Integer> multiplySource = device(source, other).checkpoint();
        Flux<Integer> plusSource = plus(source).checkpoint();

        plusSource.subscribe(data -> log.info("plusSource data: {}", data));
    }

    private static Flux<Integer> device(Flux<Integer> source, Flux<Integer> other) {
        return source.zipWith(other, (x, y) -> x / y);
    }

    private static Flux<Integer> plus(Flux<Integer> source) {
        return source.map(num -> num + 2);
    }
}
