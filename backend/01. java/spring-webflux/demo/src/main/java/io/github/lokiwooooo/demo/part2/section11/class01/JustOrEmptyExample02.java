package io.github.lokiwooooo.demo.part2.section11.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * justOrEmpty() 의 사용 예제
 *  - justOrEmpty()에 null 값을 입력하면 NullPointerException이 발생하지 않고, onNext emit 없이 onComplete만 emit 한다.
 *
 */
@Slf4j
public class JustOrEmptyExample02 {
    public static void main(String[] args) {
        Mono
                .justOrEmpty(null)
                .log() // justOrEmpty 여기서 발생하는 signal을 알수 있음
                .subscribe(data -> log.info("data: {}", data));
    }
}
