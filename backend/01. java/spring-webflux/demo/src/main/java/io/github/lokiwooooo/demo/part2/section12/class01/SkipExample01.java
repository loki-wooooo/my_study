package io.github.lokiwooooo.demo.part2.section12.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * skip 기본 개념 예제
 * - 파라미터로 입력한 숫자만큼 Upstream에서 emit 되는 데이터를 건너뛴 후, 건너뛴 다음(next) 데이터부터 downstream으로 emit 한다.
 *
 */
@Slf4j
public class SkipExample01 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofSeconds(1))
                .doOnNext(data -> log.info("on-next: {}", data))
                .skip(3)
                .subscribe(data -> log.info("next: {}", data));

        Thread.sleep(5000L);
    }
}
