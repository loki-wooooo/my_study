package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;


public class AwaitTest {

    // 생산자쪽에서 완료 통지를 보낼때까지 대기 한 후, 완료 및 통지된 데이터 개수를 검증하는 예제
    @Test
    public void awaitTest01() throws InterruptedException{
        Observable.interval(100L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error))
                .test()
                .await() // 파라미터 지정이 없다면, 생산자쪽에서 모두 통지 될떄까지 대기
                .assertComplete()
                .assertValueCount(5)
                ;
    }

    // 지정된 시간동안 대기하면서, 대기 시간내에 완료 통지를 받았는지 여부를 검증하는 예제
    @Test
    public void awaitTest02() throws InterruptedException{
        boolean isResult = Observable.interval(1000L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error))
                .test()
                .await(2000L, TimeUnit.MICROSECONDS)
                ;

        assertThat(isResult, is(true));
    }
}
