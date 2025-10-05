package io.github.lokiwooooo.chapter05.aggregation;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

/*
* 통지되는 과정을 확인하기 위해 : scan 함수를 사용함
* */
public class ObservableScanExample03 {
    public static void main(String[] args) {
        Observable.just("a", "b", "c", "d", "e")
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .scan((x, y) -> "(" + x + ", " + y + ")") // 로그 출력이 없어도, scan함수가 출력되어 값들을 소비자쪽에 통지함
                .subscribe(result -> Logger.log(LogType.ON_NEXT, result));
    }
}
