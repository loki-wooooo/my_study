package io.github.lokiwooooo.demo.part1.class02;

import reactor.core.publisher.Mono;

/*
* Mono의 기본 개념 예제
* - 1개의 데이터를 생성해서 emit 한다
* */
public class MonoExample01 {
    public static void main(String[] args) {
        Mono.just("Hello Reactor") // publisher mono
                .subscribe(message -> System.out.println(message));
    }
}
