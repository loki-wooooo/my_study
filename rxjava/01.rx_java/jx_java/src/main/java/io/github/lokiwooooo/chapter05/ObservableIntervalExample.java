package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ObservableIntervalExample {
    public static void main(String[] args) throws Exception{
        Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS)
                .map(num -> num + " count")
                .subscribe(data -> Logger.getGlobal().info(data));
        Thread.sleep(1000L);
    }
}
