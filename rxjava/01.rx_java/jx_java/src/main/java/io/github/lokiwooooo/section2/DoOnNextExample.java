package io.github.lokiwooooo.section2;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;

// 데이터 통지시 마다 실행하는 doOnNext 를 이용해 데이터의 상태를 확인하는 예제
// 단계별로 통지를함,
public class DoOnNextExample {
    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7, 9, 10, 11, 12, 13)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# 원본통지 데이터 : " + data))
                .filter(data -> data < 10)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# filter 적용 후: " + data))
                .map(data -> "### " + data + " ###")
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# map 적용 후: " + data))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, "# 최종 데이터: " + data));
    }
}
