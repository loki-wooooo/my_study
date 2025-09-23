package io.github.lokiwooooo.chapter05.error;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableOnErrorResumeNextExample {
    public static void main(String[] args) throws Exception {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MICROSECONDS)
                        .doOnNext(i -> {
                            System.out.println("num: " + num + ", i: " + i);
                        })
                        .take(5) // 5개를 통지함
                        .map(i -> num / i) //숫자를 0으로 나눌수 없어서 에러가남(강제 에러처리)
                        .onErrorResumeNext(throwable -> { // 생산자에서 에러를 먼저 처리함.
                            System.out.println("계산처리 에러발생: " + throwable); // 추가 작업이 가능함
                            return Observable.interval(200L, TimeUnit.MICROSECONDS).take(5).skip(1).map(i -> num / i);
                        })
                )
                .subscribe(
                        data -> System.out.println(data)
                );
        Thread.sleep(1000L);
    }
}
