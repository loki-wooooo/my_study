package io.github.lokiwooooo.chapter05.utility;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableDeplaySubscriptionExample {

    public static void main(String[] args) throws Exception {
        Observable.just(1, 3, 4, 6)
                .doOnNext(System.out::println) //생산한 시간을 보기위해
                .delaySubscription(2000L, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);

        Thread.sleep(2500L);
    }
}
