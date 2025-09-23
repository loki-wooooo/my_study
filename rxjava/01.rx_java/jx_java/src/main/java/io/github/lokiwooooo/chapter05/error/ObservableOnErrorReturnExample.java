package io.github.lokiwooooo.chapter05.error;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableOnErrorReturnExample {
    public static void main(String[] args) throws Exception {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MICROSECONDS)
                        .doOnNext(i -> {
                            System.out.println("num: " + num + ", i: " + i);
                        })
                        .take(5) // 5개를 통지함
                        .map(i -> num / i) //숫자를 0으로 나눌수 없어서 에러가남(강제 에러처리)
                        .onErrorReturn(exception -> { // 생산자에서 에러를 먼저 처리함.
                            if (exception instanceof ArithmeticException) {
                                System.out.println("계산처리 에러발생: " + exception.getMessage());
                            }
                            return -1L;
                        })
                )
                .subscribe(
                        data -> {
                            if (data < 0) {
                                System.out.println("# 예외를 알리는 데이터: " + data);
                            } else {
                                System.out.println("data: " + data);
                            }
                        },
                        error -> System.out.println("error: " + error.getMessage()),
                        () -> System.out.println("complete")
                );
        Thread.sleep(1000L);
    }
}
