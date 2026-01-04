package io.github.lokiwooooo.demo.part1.class02;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/*
* Sinks.Many 예제
* - replay()를 사용해서 이미 emit된 데이 중에서 특정 개수의 최신 데이터만 전달하는 예제
* */
public class SinkManyExample04 {
    public static void main(String[] args) {
        // 구독 이후, emit 된 데이터 중에서 최신 데이터 2개만 replay 한다.
        Sinks.Many<Integer> replaySink = Sinks.many().replay().limit(2);
        Flux<Integer> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        replaySink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        replaySink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> System.out.println("subscribe1 data: " + data));

        replaySink.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> System.out.println("subscribe2 data: " + data));

    }
}
