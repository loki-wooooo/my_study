package io.github.lokiwooooo.chapter05.utility;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.github.lokiwooooo.utils.TimeUtil;
import io.reactivex.Observable;

public class ObservableDelayExample02 {
    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7)
                .flatMap(item -> {
                    TimeUtil.sleep(1000L);
                    return Observable.just(item); // 새로운 Observable의 통지 시점에, 원본 데이터를 통지한다.
                }).subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
