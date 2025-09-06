package io.github.lokiwooooo.chapter05.map;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableConcatMapExample01 {
    public static void main(String[] args) throws Exception {
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .skip(2)
                .concatMap(
                        num -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                                .take(10)
                                .skip(1)
                                .map(row -> num + " * " + row + " = " + (num * row))
                ).subscribe(
                        data -> System.out.println(data),
                        error -> {},
                        () -> {

                        }
                );
        Thread.sleep(3000L);
    }
}
