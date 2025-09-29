package io.github.lokiwooooo.chapter05.utility;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.github.lokiwooooo.utils.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableTimeOutExample {
    public static void main(String[] args) {
        Observable.range(1, 5)
                .map(num -> {
                    long time = 1000L;
                    if(num ==4){
                        time = 1500L;
                    }
                    TimeUtil.sleep(time);
                    return num;
                })
                .timeout(1200L, TimeUnit.MICROSECONDS)
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error)
                );

        TimeUtil.sleep(4000L);
    }
}
