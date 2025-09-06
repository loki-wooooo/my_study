package io.github.lokiwooooo.chapter05.tomap;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Map;

public class ObservableToMapExample02 {
    public static void main(String[] args) {
        Single<Map<String, String>> single =
                Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                        
                        // key,value 셋팅
                        .toMap(data -> data.split("-")[0],
                                data -> data.split("-")[1]);

        single.subscribe(System.out::println);
    }
}
