package io.github.lokiwooooo.demo.part1.section9.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
* checkpoint(description) Operator를 이용한 예제
* - description 을 추가해서 에러가 발생한 지점을 구분할 수 있다.
* - description 을 지정할 경우에 에러가 발생한 assembly 지점의 trackback 을 추가하지 않는다.
* */
@Slf4j
public class CheckpointExample04 {
    public static void main(String[] args) {
        Flux.just(2,4,6,8)
                .zipWith(Flux.just(1,2,3,0), (x, y) -> x / y)
                .checkpoint("CheckpointExample02.zipWith.checkpoint")
                .map(num -> num + 2)
                .subscribe(data -> log.info("data: {}", data));
    }
}
