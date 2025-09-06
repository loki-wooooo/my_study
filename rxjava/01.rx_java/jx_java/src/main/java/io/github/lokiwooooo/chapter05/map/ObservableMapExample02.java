package io.github.lokiwooooo.chapter05.map;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class ObservableMapExample02 {
    public static void main(String[] args) {
        Observable.just("korea", "america", "canada", "paris", "japan", "china")
                .filter(country -> country.length() == 5)
                // 첫번쨰 글자만 대문자
                .map(country -> country.toUpperCase().charAt(0) + country.substring(1))
                .subscribe(System.out::println);
    }
}
