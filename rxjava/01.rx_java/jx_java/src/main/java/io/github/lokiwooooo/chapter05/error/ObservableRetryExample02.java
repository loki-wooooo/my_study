package io.github.lokiwooooo.chapter05.error;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableRetryExample02 {
    private final static int MAX_RETRY_COUNT = 5;
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
                        .retry((retryCount, e) -> {
                            System.out.println("# 재시도 횟수: "+ retryCount);
                            Thread.sleep(1000L); // 서버쪽 요청시 클라이언트요청을 처리하기 위한 시간이 필요함 > 시간적 여유를 주기 위해 처리
                            return retryCount < MAX_RETRY_COUNT ? true : false;
                        })
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
