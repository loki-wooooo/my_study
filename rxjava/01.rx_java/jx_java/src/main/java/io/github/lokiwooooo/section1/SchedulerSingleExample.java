package io.github.lokiwooooo.section1;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

// 신규 스레드를 만드는데 "1"개만 만들어서 사용함
// 생성된 단 하나의 스레드에서 순차 처리를 함
// trampoline vs single 스레드를 생성하느냐 안하냐 차이
public class SchedulerSingleExample {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1","2","3","4","5");

        observable.subscribeOn(Schedulers.single())
                .map(data -> "## " + data + " ##")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.single())
                .map(data -> "$$ " + data + " $$")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
