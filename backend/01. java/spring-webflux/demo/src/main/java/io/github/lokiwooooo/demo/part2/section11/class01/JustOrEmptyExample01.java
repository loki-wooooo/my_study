package io.github.lokiwooooo.demo.part2.section11.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * just()에 null 값을 입력하면 NullPointerException이 발생합니다.
 *
 */
@Slf4j
public class JustOrEmptyExample01 {
    public static void main(String[] args) {
        Mono
                .just(null)
                .log()
                .subscribe(data -> log.info("data: {}", data));
    }
}
