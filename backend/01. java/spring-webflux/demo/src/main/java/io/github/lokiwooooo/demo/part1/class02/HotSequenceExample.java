package io.github.lokiwooooo.demo.section4.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
public class HotSequenceExample {
    public static void main(String[] args) throws Exception {
        Flux<String> hotFlux = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
                .delayElements(Duration.ofSeconds(1)).share();// share() 원본 Flux를 여러 subscribe가 공유하게 됨 cold -> hot 변경

        hotFlux.subscribe(spel -> log.info("sub1: {}", spel));
        Thread.sleep(2500);

        hotFlux.subscribe(spel -> log.info("sub2: {}", spel));
        Thread.sleep(3000);

    }
}
