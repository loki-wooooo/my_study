package io.github.lokiwooooo.chapter06;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.subjects.BehaviorSubject;

// 구독시점에 이미 통지된 데이터가 있다면, 이미 통지된 데이터의 마지막 데이터를 전달 받은 후,
// 구독 이후부터 통지 된 데이터를 전달 받는 예제,
public class BehaviorSubjectExample {
    public static void main(String[] args) {
        //createDefault -> 생성과동시에 통지함
        BehaviorSubject<Integer> subject = BehaviorSubject.createDefault(3000);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "Consumer 1 : " + price));
        subject.onNext(3500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "Consumer 2 : " + price));
        subject.onNext(3300);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "Consumer 3 : " + price));
        subject.onNext(3400);
    }
}
