package io.github.lokiwooooo.chapter03;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class CompletableLambdaExample {
    public static void main(String[] args) throws InterruptedException{
        Completable completable = Completable.create(emitter -> {
            int sum = 0;
            for(int i=0; i<10; i++) {
                sum += i;
            }
            System.out.println("완료");
            emitter.onComplete();
        });

        //의 작업(sum 계산)을 **백그라운드의 computation 스레드**에서 실행하도록 지정 `Completable`
        completable.subscribeOn(Schedulers.computation())
        .subscribe(
            () -> System.out.println("완료"),
            error -> System.err.println("에러")
        );

        Thread.sleep(1000L);
    }
}
