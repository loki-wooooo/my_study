package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

// assertNoValues를 이용한 데이터 검증 예제
public class AssertNoValuesTest {

    @Test
    public void assertNoValuesTest() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .filter(data -> data > 5)
                .test()
                .awaitDone(1000L, TimeUnit.MICROSECONDS)
                .assertNoValues();
    }
}
