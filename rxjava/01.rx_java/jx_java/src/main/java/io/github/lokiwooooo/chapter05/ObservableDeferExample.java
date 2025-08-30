package io.github.lokiwooooo.chapter05;

import io.reactivex.Observable;

import java.time.LocalTime;

public class ObservableDeferExample {
    public static void main(String[] args) throws Exception {

        //실행 시점에 통보
        //**defer()**는 구독될 때마다 매번 새로운 Observable을 만들어 각 구독자에게 신선한 데이터 또는 상태를 제공
        Observable<LocalTime> observable = Observable.defer(() -> {
            LocalTime now = LocalTime.now();
            return Observable.just(now);
        });

        //선언 시점에 통보
        Observable<LocalTime> observable2 = Observable.just(LocalTime.now());

        //구독한 시점에 통보
        observable.subscribe(System.out::println);

        observable2.subscribe(System.out::println);

        Thread.sleep(3000L);

        //구독한 시점에 통보
        observable.subscribe(System.out::println);

        observable2.subscribe(System.out::println);
    }
}
