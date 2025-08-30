package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

//전체적인 처리시간을 줄일 수 있음
public class ObservableFromFutureExample {
    public static void main(String[] args) throws Exception {

        Future<Double> future = longTimeWork();

        shortTiemWork();

        // 비동기 처리
        Observable.fromFuture(future)
                .subscribe(System.out::println);
    }

    public static CompletableFuture<Double> longTimeWork() throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return calculate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Double calculate() throws Exception {
        Thread.sleep(1000L);
        return 10000000000.0;
    }

    private static void shortTiemWork() {

    }
}
