package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestObserverEventTest {

    // 완료 통지 이벤트가 발생해서 종료 되었는지를 검증하는 예제
    @Test
    public void isTerminalEventTest() {
        boolean isResult = Observable.interval(200L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error))
                .test()
                .awaitCount(5)
                .isTerminated();

    }

    // 에러 통지 이벤트가 발생해서 종료 되었는지를 검증하는 예제
    @Test
    public void isErrorEventTest() {
        boolean isResult = Observable.interval(200L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .map(data -> {
                    if (data == 2) {
                        throw new RuntimeException("error happened");
                    }
                    return data;
                })
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error))
                .test()
                .awaitCount(5)
                .isTerminated() // 통지도중 어떤 예외가 발생했는지 체크를 할 수 있음
                ;
    }
}
