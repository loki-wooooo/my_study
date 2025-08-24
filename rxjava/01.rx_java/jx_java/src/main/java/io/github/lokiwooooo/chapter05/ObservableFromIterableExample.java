package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class ObservableFromIterableExample {
    public static void main(String[] args) {
        // Arraylist 파라미터로 받는다.

        List<String> contiries = Arrays.asList("Korea", "USA", "Japan", "Italy");

        Observable.fromIterable(contiries)
                .subscribe(System.out::println);;
    }
}
