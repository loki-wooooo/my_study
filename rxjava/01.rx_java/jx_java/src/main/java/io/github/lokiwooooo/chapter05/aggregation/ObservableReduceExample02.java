package io.github.lokiwooooo.chapter05.aggregation;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

public class ObservableReduceExample02 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                // seed라고 초기값을 입력할 수 있음
                .reduce(0, (x, y) -> {
                    return x + y;
                })
                .subscribe(result -> Logger.log(LogType.ON_NEXT, result));
    }
}
