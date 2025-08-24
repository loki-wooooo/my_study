package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObserableTimerExample {

    public static void main(String[] args) throws Exception {
        Observable<String> observable =
                Observable.timer(2000, TimeUnit.MICROSECONDS)
                        .map(count -> "DO work!");

        observable.subscribe(System.out::println);

        Thread.sleep(3000L);
    }

}
