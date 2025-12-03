package io.github.lokiwooooo.demo.section4.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
public class ColdSequenceExample {
    public static void main(String[] args) {
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("a","b","c"))
                .map(String::toUpperCase);

        coldFlux.subscribe(spel -> log.info("sub1: {}", spel));
        log.info("-------------------------------------------------");
        coldFlux.subscribe(spel -> log.info("sub2: {}", spel));
    }
}
