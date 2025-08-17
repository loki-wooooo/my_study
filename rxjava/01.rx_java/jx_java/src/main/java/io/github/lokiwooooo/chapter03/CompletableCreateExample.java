package io.github.lokiwooooo.chapter03;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class CompletableCreateExample {

    public static void main(String[] args) throws InterruptedException {
        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                //데이터를 통지하는게 아니라, 특정 작업을 수행 후 완료 통지
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum += i;
                }
                System.out.println("sum: " + sum);

                emitter.onComplete();
            }
        });

        completable.subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                //아무것도 하지 않음
            }

            @Override
            public void onComplete() {
                System.out.println("완료");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("에러");
            }
        });

        Thread.sleep(1000L);
    }
}
