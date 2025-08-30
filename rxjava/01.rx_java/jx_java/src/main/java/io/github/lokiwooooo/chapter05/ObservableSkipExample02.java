package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableSkipExample02 {
    public static void main(String[] args) throws Exception{
        Observable.interval(300L, TimeUnit.MILLISECONDS)
                .skip(1000L, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);

        Thread.sleep(3000L);
    }
}
