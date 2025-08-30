package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.CarMaker;
import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

public class ObservableFilterExample02 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker() == CarMaker.CHEVROLET)
                .filter(car -> car.getCarPrice() > 300000000)
                .subscribe(car -> System.out.println(car.getCarMaker() + " : " + car.getCarName()));
    }
}
