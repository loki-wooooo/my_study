package io.github.lokiwooooo.demo.part2.section12.class04;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * 파라미터로 입력된 Publisher가 onNext 또는 onComplete signal을 발생시킬 때까지 Upstream에서 emit된 데이터만
 * DownStream에 emit 한다.
 *
 */
@Slf4j
public class TakeUntilOtherExample {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(300))
                .takeUntilOther(doSomeTask())
                .subscribe(data -> log.info("data: {}", data));

        Thread.sleep(2000);
    }

    private static Publisher<?> doSomeTask() {
        return Mono.empty().delay(Duration.ofSeconds(1));
    }
}
