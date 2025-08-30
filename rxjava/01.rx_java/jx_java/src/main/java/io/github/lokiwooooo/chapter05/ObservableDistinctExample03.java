package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

public class ObservableDistinctExample03 {
    public static void main(String[] args) {
        //중복된 carmaker내용만 받아서 처리
        Observable.fromIterable(SampleData.carList)
                .distinct(car -> car.getCarMaker())
                .subscribe(System.out::println);
    }
}
