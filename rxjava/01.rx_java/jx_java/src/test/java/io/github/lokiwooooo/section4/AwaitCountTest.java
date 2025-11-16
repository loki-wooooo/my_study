package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class AwaitCountTest {

    // 지정된 개수만큼 대기하고 완료통지 유무, 통지된 데이터 개수 및 데이터의 값과 순서를 검증하는 예제
    @Test
    public void awaitCountTest() {
        Observable.interval(200L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error))
                .test()
                .awaitCount(3) // 생산자에서 통지하는 개수
                .assertNotComplete()
                .assertValueCount(3) // 생산자는 5개를 통지해야 완료지만 소비자입장에서 3개만 받으려도 대기하기 때문에 false
                .assertValues(0L, 1L, 2L) // 통지되는 데이터의 값이 맞는지 확인하는 유/무
                ;
    }
}
