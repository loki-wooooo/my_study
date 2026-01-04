package io.github.lokiwooooo.demo.part1.setcion10.class03;

import reactor.core.publisher.Mono;

public class PublisherProbeExample {

    public static Mono<String> processWith(final Mono<String> main, final Mono<String> standby) {
        return main
                .flatMap(message -> Mono.just(message))
                .switchIfEmpty(standby); // emit 데이터가 없다면, standby 값이 실행됨
    }

    public static Mono<String> useMainPower(){
        return Mono.empty();
    }

    public static Mono useStandbyPower() {
        return Mono.just("# use Standby Power");
    }
}
