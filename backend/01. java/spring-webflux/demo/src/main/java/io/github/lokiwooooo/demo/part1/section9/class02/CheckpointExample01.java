package io.github.lokiwooooo.demo.section9.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
* checkpoint() Operator 를 이용한 예제
* - 에러가 예상되는 assembly 지점에 checkpoint()를 사용해서 여러 발생 지침을 확인할 수 있다.
* - checkpoint()는 에러 발생 시, traceback 이 추가된다.
* */
@Slf4j
public class CheckpointExample01 {
    public static void main(String[] args) {
        Flux.just(2,4,6,8)
                .zipWith(Flux.just(1,2,3,0), (x, y) -> x / y)
                .checkpoint() // 에러발생 지점을 확
                .map(num -> num + 2)
                .subscribe(data -> log.info("data: {}", data));
    }
}
