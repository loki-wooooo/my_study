package io.github.lokiwooooo.demo.part1.setcion10.class01;

import reactor.core.publisher.Flux;

public class GeneralExample {

    public static Flux<String> sayHelloReactor() {
        return Flux.just("Hello", "Reactor");
    }

    public static Flux<Integer> deviceByTwo(Flux<Integer> source) {
        return source
                .zipWith(Flux.just(2, 2, 2, 2, 2), (x, y) -> x / y);
    }

    public static Flux<Integer> occurError(Flux<Integer> source) {
        return source
                .zipWith(Flux.just(2, 2, 2, 2, 0), (x, y) -> x / y);
    }

    public static Flux<Integer> takeNumber(Flux<Integer> source, Long n) {
        return source
                .take(n);
    }
}
