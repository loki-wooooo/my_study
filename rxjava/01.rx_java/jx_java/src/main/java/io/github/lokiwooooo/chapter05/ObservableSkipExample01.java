package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

public class ObservableSkipExample01 {
    public static void main(String[] args) {
        Observable.range(1, 10)
                .skip(5)
                .subscribe(System.out::println);
    }
}
