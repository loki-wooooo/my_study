package io.github.lokiwooooo.chapter03;

import java.util.Date;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.disposables.Disposable;

public class MaybeCreateExample {
    public static void main(String[] args) {
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onSuccess(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            }
        });
        
        maybe.subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //아무것도 하지 않음
            }

            //onNext -> onScueess로 사용함
            @Override
            public void onSuccess(String t) {
                System.out.println("# 날짜 시간: " + t);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("# 에러: " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("# 완료");
            }
        });
    }
}
