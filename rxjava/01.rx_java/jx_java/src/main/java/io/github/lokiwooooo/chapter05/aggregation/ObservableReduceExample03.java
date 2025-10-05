package io.github.lokiwooooo.chapter05.aggregation;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

/*
 * 과정없이, 결과만 보여줌 : reduce 함수를 사용함
 * */
public class ObservableReduceExample03 {
    public static void main(String[] args) {
        Observable.just("a", "b", "c", "d", "e")
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce((x, y) -> "(" + x + ", " + y + ")")
                .subscribe(result -> Logger.log(LogType.ON_NEXT, result));
    }
}
