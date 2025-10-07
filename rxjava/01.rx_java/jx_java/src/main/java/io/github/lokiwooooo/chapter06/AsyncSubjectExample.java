package io.github.lokiwooooo.chapter06;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.subjects.AsyncSubject;

// 구독시점과는 상관없이, 모든 소비자들이 마지막으로 통지된 데이터만 전달 받는 AsyncSubject 예제
public class AsyncSubjectExample {
    public static void main(String[] args) {
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.onNext(1000);

        subject.doOnNext(price -> Logger.log(LogType.ON_NEXT, "Consumer 1 : " + price))
                .subscribe(price -> Logger.log(LogType.ON_NEXT, "Consumer 1 : " + price));
        subject.onNext(2000);

        subject.doOnNext(price -> Logger.log(LogType.ON_NEXT, "Consumer 2 : " + price))
                .subscribe(price -> Logger.log(LogType.ON_NEXT, "Consumer 2 : " + price));
        subject.onNext(3000);

        subject.doOnNext(price -> Logger.log(LogType.ON_NEXT, "Consumer 3 : " + price))
                .subscribe(price -> Logger.log(LogType.ON_NEXT, "Consumer 3 : " + price));
        subject.onNext(4000);

        // 생산자에서 완료시점을 통지를 했음에도 불구하고, 소비자쪽에 마지막에 통지한 데이터를 통지함.
        subject.onComplete();

        subject.doOnNext(price -> Logger.log(LogType.ON_NEXT, "Consumer 4 : " + price))
                .subscribe(price -> Logger.log(LogType.ON_NEXT, "Consumer 4 : " + price));
    }
}
