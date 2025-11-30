package io.github.lokiwooooo.demo;

import io.github.lokiwooooo.demo.util.LogType;
import io.github.lokiwooooo.demo.util.Logger;
import reactor.core.publisher.Flux;


public class HelloReactor {
    public static void main(String[] args) {
//        Mono.just("Hello Reactor") // publisher mono
//                .subscribe(message -> System.out.println(message));

        // Flux -> publisher

        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence
                .map(data -> data.toLowerCase())
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

    }
}
