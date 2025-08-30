package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

public class ObservableDistinctExample01 {
    public static void main(String[] args) {
        //배열을 입력을 전달받아 데이터를 통지
        Observable.fromArray(SampleData.carMakersDuplicated)
                .distinct()
                .subscribe(System.out::println);
    }
}
