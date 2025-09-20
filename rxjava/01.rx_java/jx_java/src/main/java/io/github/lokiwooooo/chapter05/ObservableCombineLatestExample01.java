package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableCombineLatestExample01 {
    public static void main(String[] args) throws Exception {
        Observable<Long> observable1 = Observable.interval(500L, TimeUnit.MILLISECONDS)
                .take(4);


        Observable<Long> observable2 = Observable.interval(700L, TimeUnit.MILLISECONDS)
                .take(6)
                .map(num -> num + 1000);


        Observable.combineLatest(
                        observable1
                        , observable2
                        , (num1, num2) -> "data1: " + num1 + "\tdata2: " + num2)
                .subscribe(System.out::println);

        Thread.sleep(3000);

    }
}
