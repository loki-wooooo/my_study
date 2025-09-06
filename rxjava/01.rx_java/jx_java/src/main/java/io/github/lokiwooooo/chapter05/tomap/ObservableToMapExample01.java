package io.github.lokiwooooo.chapter05.tomap;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Map;

public class ObservableToMapExample01 {
    public static void main(String[] args) {
        Single<Map<String, String>> single =
                Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                        .toMap(data -> data.split("-")[0]); // 값에 key만 추가해서 map으로 등록됨

        single.subscribe(System.out::println);
    }
}
