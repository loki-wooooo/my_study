package io.github.lokiwooooo.chapter05.map;

import io.reactivex.Observable;

public class ObservableFlatMapExample03 {
    public static void main(String[] args) {
        Observable.range(2, 1)
                .flatMap(
                        data -> Observable.range(1, 9),
                        (sourceData, transformData) -> sourceData + " * " + transformData + " = " + (sourceData * transformData)
                )
                .subscribe(System.out::println);
    }
}
