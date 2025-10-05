package io.github.lokiwooooo.chapter05.aggregation;

import io.github.lokiwooooo.common.SampleData;
import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

import java.util.Arrays;

public class ObservableCountExample02 {
    public static void main(String[] args) {

        // concat -> 3개의 Observable을 1개로 결합함
        Observable.concat(
                        Arrays.asList(
                                Observable.fromIterable(SampleData.seoulPM10List),
                                Observable.fromIterable(SampleData.busanPM10List),
                                Observable.fromIterable(SampleData.incheonPM10List)
                        )
                )
                .count()
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));


    }
}
