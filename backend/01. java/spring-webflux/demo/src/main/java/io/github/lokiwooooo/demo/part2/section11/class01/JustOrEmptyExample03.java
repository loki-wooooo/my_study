package io.github.lokiwooooo.demo.part2.section11.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * justOrEmpty() 의 사용 예제
 * - justOrEmpty()에 Optional.isPresent() 가 true 가 아니라면, onNext emit 없이 onComplete 만 emit 한다.
 *
 */
@Slf4j
public class JustOrEmptyExample03 {
    public static void main(String[] args) {
        Mono
                .justOrEmpty(Optional.ofNullable(null))
                .log() // justOrEmpty 여기서 발생하는 signal을 알수 있음
                .subscribe(data -> log.info("data: {}", data));
    }
}
