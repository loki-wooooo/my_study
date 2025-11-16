package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class AwaitDoneTest {

    //지정된 시간까지 완료 통지가 없이, 해당 시점까지 전달 받은 데이터의 개수가 맞는지 검증하는 예제
    @Test
    public void awaitDoneTest01() {
        Observable.interval(200L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error))
                .test()
                .awaitDone(500L, TimeUnit.MICROSECONDS) // 대기하면서 검증하는 예제 코드
                .assertNotComplete()
                .assertValueCount(2)
                ;
    }

    //지정된 시간까지 완료 통지가 있어, 완료 통지 시점까지만 대기하고 전달 받은 데이터의 개수가 맞는지 검증하는 예
    @Test
    public void awaitDoneTest02() {
        Observable.interval(200L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error))
                .test()
                .awaitDone(1500L, TimeUnit.MICROSECONDS) // 해당 파라미터 이전에 조건이 부합하는지 확인
                .assertComplete()
                .assertValueCount(5)
                ;
    }
}
