package io.github.lokiwooooo.section1;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

// 현재스레드에서 Queue를 이용해서 작업을 처리함
public class SchedulerTrampolineExample {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1","2","3","4","5");

        observable.subscribeOn(Schedulers.trampoline())
                .map(data -> "## " + data + " ##")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.trampoline())
                .map(data -> "$$ " + data + " $$")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
