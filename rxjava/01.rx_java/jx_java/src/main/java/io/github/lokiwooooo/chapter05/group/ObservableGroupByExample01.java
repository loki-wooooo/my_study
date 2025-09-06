package io.github.lokiwooooo.chapter05.group;

import io.github.lokiwooooo.common.Car;
import io.github.lokiwooooo.common.CarMaker;
import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class ObservableGroupByExample01 {
    public static void main(String[] args) {
        Observable<GroupedObservable<CarMaker, Car>> observable =
                // Car::getCarMaker -> group 으로 묶어주는 key
                Observable.fromIterable(SampleData.carList).groupBy(Car::getCarMaker);

        observable.subscribe(
                groupedObservable -> groupedObservable.subscribe(
                        car -> new StringBuilder().append("Group ").append(groupedObservable.getKey()).append("Car Name: ").append(car.getCarName()).toString()
                )
        );
    }
}
