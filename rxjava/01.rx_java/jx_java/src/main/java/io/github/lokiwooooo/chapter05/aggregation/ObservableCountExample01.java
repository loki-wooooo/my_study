package io.github.lokiwooooo.chapter05.aggregation;

import io.github.lokiwooooo.common.SampleData;
import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

public class ObservableCountExample01 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .count()
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
