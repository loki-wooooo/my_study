package io.github.lokiwooooo.demo.part2.section12.class02;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * skipuntilohter 기본 예제
 * - 파라미터로 입력된 Publisher가 onNext또는 onComplete Signal을 발생시킬 때까지 upstream에서 emit된 데이터를 건너뛴다.
 * 자주는 사용될것 같지는 않음(*)
 */
@Slf4j
public class SkipUntilOtherExample03 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofSeconds(1))
                .skipUntilOther(doSomeTask())
                .subscribe(data -> log.info("data :: {}", data))
        ;

        Thread.sleep(4000);
    }

    // Publisher mono/flux 둘중 하나가 될 수 있음
    public static Publisher<?> doSomeTask() {
        return Mono.empty().delay(Duration.ofMillis(2500));
    }
}
