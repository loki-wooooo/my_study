package io.github.lokiwooooo.chapter05.list;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public class ObservableToListExample01 {
    public static void main(String[] args) {
        Single<List<Integer>> single = Observable.just(1,3,5,7,9).toList();
        single.subscribe(System.out::println);
    }
}
