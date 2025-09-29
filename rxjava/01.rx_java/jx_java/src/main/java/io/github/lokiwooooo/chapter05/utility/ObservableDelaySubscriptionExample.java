package io.github.lokiwooooo.chapter05.utility;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.github.lokiwooooo.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableDelaySubscriptionExample {
    public static void main(String[] args) {
        Logger.log(LogType.PRINT, "# 실행 시작 시간: " + TimeUtil.getCurrentTimeFormatted());

        Observable.just(1, 3, 4, 6)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .delaySubscription(2000L, TimeUnit.MILLISECONDS)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2500L);
    }
}
