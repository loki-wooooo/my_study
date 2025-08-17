package io.github.lokiwooooo.chapter02;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class MissingBackpressureExceptionExample {
    public static void main(String[] args) throws InterruptedException{
        Flowable.interval(1L, TimeUnit.MICROSECONDS)
                //interval에서 호출 하는 콜백 함수 doOnNext
                .doOnNext(data -> System.out.println(data))
                //스케줄러 챕터에서 이야기 다시함(데이터를 처리하는 스레드를 분리할 수 있음)
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                            System.out.println("데이터 처리중...");
                            TimeUnit.SECONDS.sleep(1L);
                            System.out.println(data);
                },
                        error -> System.err.println("에러")
                , () -> System.out.println("OK"));
        Thread.sleep(2000L);
    }
}
