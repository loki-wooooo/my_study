package io.github.lokiwooooo.demo;

import reactor.core.publisher.Mono;

public class HelloReactor {
    public static void main(String[] args) {
        Mono.just("Hello Reactor") // publisher mono
                .subscribe(message -> System.out.println(message));
    }
}
