package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;
/**
 * 파라미터로 받은 Flowable/Observable이 최초로 데이터를 발행할 떄까지 계속 데이터를 발행
 * */
public class ObservableTakeUntilExample02 {
    public static void main(String[] args) throws Exception {
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .takeUntil(Observable.timer(5500L, TimeUnit.MILLISECONDS))
                .subscribe(System.out::println);

        Thread.sleep(5500L);
    }
}
