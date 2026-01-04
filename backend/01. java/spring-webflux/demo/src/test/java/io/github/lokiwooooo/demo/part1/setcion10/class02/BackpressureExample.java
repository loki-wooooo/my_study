package io.github.lokiwooooo.demo.part1.setcion10.class02;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class BackpressureExample {
    public static Flux<Integer> generateNumberByErrorStrategy() {
        return Flux
                .create(emitter -> {
                    for (int i = 1; i <= 100; i++) {
                        emitter.next(i);
                    }
                    emitter.complete();
                }, FluxSink.OverflowStrategy.ERROR);
    }

    public static Flux<Integer> generateNumberByDropStrategy() {
        return Flux
                .create(emitter -> {
                    for (int i = 1; i <= 100; i++) {
                        emitter.next(i);
                    }
                    emitter.complete();
                }, FluxSink.OverflowStrategy.DROP);
    }
}
