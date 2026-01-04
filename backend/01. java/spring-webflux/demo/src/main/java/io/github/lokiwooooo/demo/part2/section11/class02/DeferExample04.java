package io.github.lokiwooooo.demo.part2.section11.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Defer 사용 예제
 * - 원본 데이터 소스에서 emit 되는 데이터가 없을 경우에만 Mono.defer(this::setDefault)가 실행된다.
 */
@Slf4j
public class DeferExample04 {
    public static void main(String[] args) throws InterruptedException {
        log.info("# Starting");
        Mono<Object> mono = Mono
                .empty()
                .switchIfEmpty(Mono.defer(DeferExample04::sayDefault)); // 메서드 레퍼런스를 사용함
        Thread.sleep(3000L);
        mono.subscribe(data -> log.info("data: {}", data));
    }

    public static Mono<String> sayDefault() {
        log.info("#Say HI");
        return Mono.just("HI");
    }
}
