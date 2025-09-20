package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableMergeExample02 {
    public static void main(String[] args) throws Exception{

        Observable<String> observable1 = SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA);
        Observable<String> observable2 = SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB);
        Observable<String> observable3 = SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC);

        Observable.merge(observable1, observable2, observable3)
                .subscribe(System.out::println);

        Thread.sleep(1000L);
    }
}
