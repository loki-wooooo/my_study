package io.github.lokiwooooo.demo.part1.class02;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/*
* Sinks.Many 예제
* - multicast()를 사용해서 단 하나의 Subscriber에게만 데이터를 emit하는 예제
* */
public class SinkManyExample02 {
    public static void main(String[] args) {
        // multicast - 하나 이상 Subscriber에게만 데이터를 emit할 수 있다.
        Sinks.Many<Integer> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> System.out.println("subscribe1 data: " + data));
        fluxView.subscribe(data -> System.out.println("subscribe2 data: " + data));

        multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

    }
}
