package io.github.lokiwooooo.demo.part2.section11.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Defer 사용 예제
 * - switchIfEmpty()에 파라미터로 입력하는 sequence는 업스트림에서 emit 되는 데이터가 없을 경우 다운스트림에 emit 한다.
 * - 하지만 파라미터로 입력된 setDefault()는 switchIfEmpty()가 선언된 시점에 이미 호출이 되기 떄문에
 * 다운스트림에 데이터를 emit하지는 않지만 불필요한 메서드 호출이 발생한다.
 */
@Slf4j
public class DeferExample02 {
    public static void main(String[] args) throws InterruptedException {
        log.info("# Starting");
        Mono
                .just("Hello")
                /*
                * 데이터를 발행하기 전에 설정한 시간만큼 기다리게 만드는(지연시키는) 기능
                * 데이터가 준비되었더라도, 지정된 시간이 지난 후에야 구독자에게 전달
                * */
                .delayElement(Duration.ofSeconds(2))
                .switchIfEmpty(sayDefault())
                .subscribe(data -> log.info("data: {}", data));

        Thread.sleep(2500L);
    }

    public static Mono<String> sayDefault() {
        log.info("#Say HI");
        return Mono.just("HI");
    }
}
