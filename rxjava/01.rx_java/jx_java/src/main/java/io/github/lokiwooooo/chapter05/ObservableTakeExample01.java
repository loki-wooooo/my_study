package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

public class ObservableTakeExample01 {
    public static void main(String[] args) {
        
        //take -> count만큼 꺼내라
        Observable.just("a","b","c","d")
                .take(2)
                .subscribe(System.out::println);
    }
}
