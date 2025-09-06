package io.github.lokiwooooo.chapter05.list;

import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

public class ObservableToListExample02 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .toList()
                .subscribe(System.out::println);
    }
}
