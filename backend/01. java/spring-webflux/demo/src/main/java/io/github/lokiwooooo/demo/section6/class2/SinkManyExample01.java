package io.github.lokiwooooo.demo.section6.class2;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/*
* Sinks.Many 예제
* - unicast()를 사용해서 단 하나의 Subscriber에게만 데이터를 emit하는 예제
* */
public class SinkManyExample01 {
    public static void main(String[] args) {
        // unicast - 단 하나의 Subscriber에게만 데이터를 emit할 수 있다.
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> System.out.println("subscribe1 data: " + data));

        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        // 에러
        fluxView.subscribe(data -> System.out.println("subscribe2 data: " + data));
    }
}
