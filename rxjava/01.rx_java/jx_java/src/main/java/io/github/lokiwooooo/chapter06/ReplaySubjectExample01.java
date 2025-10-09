package io.github.lokiwooooo.chapter06;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample01 {
    public static void main(String[] args) {
        //통지된 데이터를 지정하지 않은 예제
        ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.onNext(3000);
        subject.onNext(2500);

        subject.subscribe(price -> System.out.println("Consumer 1 : " + price));
        subject.onNext(3500);

        subject.subscribe(price -> System.out.println("Consumer 2 : " + price));
        subject.onNext(3300);

        subject.onComplete();

        subject.subscribe(price -> System.out.println("Consumer 3 : " + price));
    }
}
