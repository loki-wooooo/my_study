package io.github.lokiwooooo.chapter05.aggregation;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

public class ObservableReduceExample01 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce((x, y) -> x + y)
                .subscribe(result -> Logger.log(LogType.ON_NEXT, result));
    }
}
