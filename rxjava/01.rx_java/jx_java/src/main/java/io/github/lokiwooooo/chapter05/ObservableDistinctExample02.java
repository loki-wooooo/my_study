package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.CarMaker;
import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

public class ObservableDistinctExample02 {
    public static void main(String[] args) {
        //배열을 입력을 전달받아 데이터를 통지
        Observable.fromArray(SampleData.carMakersDuplicated)
                .distinct()
                .filter(carMaker -> carMaker == CarMaker.KMG)
                .subscribe(System.out::println);
    }
}
