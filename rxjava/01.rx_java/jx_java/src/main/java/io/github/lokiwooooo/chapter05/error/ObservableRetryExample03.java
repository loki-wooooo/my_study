package io.github.lokiwooooo.chapter05.error;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableRetryExample03 {
    private final static int MAX_RETRY_COUNT = 5;

    public static void main(String[] args) throws Exception {
        Observable.just(10, 12, 15, 16)
                //zipWith 결합연산자중 하나, 원본데이터 + zipwith에 있는 Observable의 결합 (index가 동일한 데이터 끼리 결합)
                // ex (10,1) (12,2) (15,0) (16,4)
                .zipWith(Observable.just(1, 2, 0, 4), (a, b) -> {
                    int result;
                    try {
                        result = a / b;
                    } catch (ArithmeticException e) {
                        System.out.println(e);
                        throw e;
                    }
                    return result;
                })
                .retry((retryCount, e) -> {
                    System.out.println("# 재시도 횟수: " + retryCount);
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
