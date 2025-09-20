package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableConcatExample01 {
    public static void main(String[] args) throws Exception{
        Observable<Long> observable1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(4);


        Observable<Long> observable2 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(num -> num + 1000);

        // 통지시간이 같다면, parameter가 빠른게 먼저 실행이됨.
        Observable.concat(observable1, observable2)
                .subscribe(System.out::println);

        Thread.sleep(4000);

    }
}
