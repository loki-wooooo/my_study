package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

public class ObservableRangeExample {
    //for, while문 대체로 사용할수 있음(블록킹 함수)
    public static void main(String[] args) {
        Observable<Integer> source = Observable.range(0, 5);
        source.subscribe(System.out::println);
    }
}
