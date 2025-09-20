package io.github.lokiwooooo.chapter05;

import io.github.lokiwooooo.common.SampleData;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class ObservableConcatExample02 {
    public static void main(String[] args) throws Exception {

        List<Observable<String>> speedPerSelectionList = Arrays.asList(
                SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA)
                , SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB)
                , SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC)
        );

        Observable.concat(speedPerSelectionList)
                .subscribe(System.out::println);

        Thread.sleep(2000L);
    }
}
