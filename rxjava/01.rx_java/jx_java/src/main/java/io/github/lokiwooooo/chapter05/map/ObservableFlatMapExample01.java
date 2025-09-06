package io.github.lokiwooooo.chapter05.map;

import io.reactivex.Observable;

public class ObservableFlatMapExample01 {
    public static void main(String[] args) {
        Observable.just("Hello")
                .flatMap(hello -> Observable.just("자바", "파이썬", "안드로이드").map(lang -> hello + " " + lang))
                .subscribe(System.out::println);
    }
}
