package io.github.lokiwooooo.demo.part1.section9.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

// non-debug mode
@Slf4j
public class DebugModeExample01 {
    public static void main(String[] args) {
        // 각파라미터의 첫번쨰, 두번째 .. 데이터들을 묶어줌 여기서는(1,2),(4,2),(6,3),(8,0)
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .subscribe(data -> log.info("data: {}", data));
    }
}
