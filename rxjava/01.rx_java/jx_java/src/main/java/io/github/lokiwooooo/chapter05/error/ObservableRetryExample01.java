package io.github.lokiwooooo.chapter05.error;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableRetryExample01 {
    public static void main(String[] args) throws Exception {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MICROSECONDS)
                        .map(i -> {
                            long result;
                            try {
                                result = num / i;
                            } catch (ArithmeticException e) {
                                System.out.println(e);
                                throw e;
                            }
                            return result;
                        })
                        .retry(5)
                        .onErrorReturn(throwable -> -1L)
                )
                .subscribe(
                        data -> System.out.println(data),
                        error -> System.out.println("error: " + error.getMessage()),
                        () -> System.out.println("complete")
                );
        Thread.sleep(5000L);
    }
}
