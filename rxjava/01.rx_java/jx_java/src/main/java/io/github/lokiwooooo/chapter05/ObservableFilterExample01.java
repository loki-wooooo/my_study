package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.CarMaker;
import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

public class ObservableFilterExample01 {
    public static void main(String[] args) {
        //arraylist
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker() == CarMaker.CHEVROLET)
                .subscribe(car -> System.out.println(car.getCarMaker() + " : " + car.getCarName()));
    }
}
