package io.github.lokiwooooo.chapter06;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample02 {
    public static void main(String[] args) {
        //통지된 데이터를 지정한 예제
        ReplaySubject<Integer> subject = ReplaySubject.createWithSize(2);
        subject.onNext(3000);
        subject.onNext(2500);

        subject.subscribe(price -> System.out.println("Consumer 1 : " + price));
        subject.onNext(3500);

        subject.subscribe(price -> System.out.println("Consumer 2 : " + price));
        subject.onNext(3300);

        subject.subscribe(price -> System.out.println("Consumer 3 : " + price));
        subject.onNext(3400);

        subject.onComplete();

        subject.subscribe(price -> System.out.println("Consumer 4 : " + price));
    }
}
