package io.github.lokiwooooo.demo.part1.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/*
* Mono의 기본 개념 예제
* - 원본 데이터의 emit 없이 onComplete signal만 emit 한다
* */
@Slf4j
public class MonoExample02 {
    public static void main(String[] args) {
        Mono.empty() // 실제 전달없이 onComplete signal 전달
                .subscribe(
                        data -> log.info("# emitted data: {}", data)
                        , error -> {}
                        , () -> log.info("# emitted onComplete signal")
                );
    }
}
