package io.github.lokiwooooo.chapter05.utility;

import io.reactivex.Observable;

public class ObservableDeplayExample02 {

    public static void main(String[] args) throws Exception {
        Observable.just(1, 3, 4, 6)
                .delay(item -> {
                    Thread.sleep(item * 1000L);
                    return Observable.just(item);
                })
                .subscribe(System.out::println);
    }
}
