package io.github.lokiwooooo;

import io.reactivex.Observable;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HelloRxJava {
    public static void main(String[] args) {
        // 데이터를 생성 및 통제(생성자쪽 코드)
        Observable<String> observable = Observable.just("Hello", "RxJava");
        //반환받은 데이터를 구독(소비자쪽 코드)
        observable.subscribe(System.out::println);
    }
}