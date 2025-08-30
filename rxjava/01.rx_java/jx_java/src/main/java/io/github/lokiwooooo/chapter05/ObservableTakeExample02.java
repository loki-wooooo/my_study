package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableTakeExample02 {
    public static void main(String[] args) throws Exception {
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .take(3500L, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);

        Thread.sleep(3500L);
    }
}
