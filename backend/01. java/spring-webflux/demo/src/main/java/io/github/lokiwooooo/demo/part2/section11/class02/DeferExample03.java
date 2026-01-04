package io.github.lokiwooooo.demo.part2.section11.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Defer 사용 예제
 * - switchIfEmpty()에 파라미터로 입력하는 sequence는 업스트림에서 emit 되는 데이터가 없을 경우 다운스트림에 emit 한다.
 * - 불필요한 호출을 방지하기 위해 실제 필요한 시점에 데이터를 emit 하도록 defer()를 사용한다.
 */
@Slf4j
public class DeferExample03 {
    public static void main(String[] args) throws InterruptedException {
        log.info("# Starting");
        Mono
                .just("Hello")
                /*
                * 데이터를 발행하기 전에 설정한 시간만큼 기다리게 만드는(지연시키는) 기능
                * 데이터가 준비되었더라도, 지정된 시간이 지난 후에야 구독자에게 전달
                * */
                .delayElement(Duration.ofSeconds(3))
                .switchIfEmpty(Mono.defer(() -> sayDefault()))
                .subscribe(data -> log.info("data: {}", data));

        Thread.sleep(3500L);
    }

    public static Mono<String> sayDefault() {
        log.info("#Say HI");
        return Mono.just("HI");
    }
}
