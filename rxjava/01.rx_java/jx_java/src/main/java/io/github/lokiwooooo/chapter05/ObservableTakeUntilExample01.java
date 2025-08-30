package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.Car;
import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

public class ObservableTakeUntilExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .takeUntil((Car car) -> car.getCarName().equals("트랙스"))
                .subscribe(System.out::println);
    }
}
