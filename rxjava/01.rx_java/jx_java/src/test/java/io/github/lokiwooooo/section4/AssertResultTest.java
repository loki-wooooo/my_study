package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.utils.LogType;
import io.github.lokiwooooo.utils.Logger;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

// assertResult를 사용하여 통지 완료 후, 통지된 데이터와 파라미터로 입력된 데이터의 값과 순서가 일치하는지 검증하는 예제
public class AssertResultTest {

    // 테스트 실패 예제
    // 완료통지가 없기 떄문에 테스트가 실패함
    @Test
    public void assertResultFailTest() {
        Observable.interval(200L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .filter(data -> data >3)
                .test()
                .awaitDone(1100L, TimeUnit.MICROSECONDS)
                .assertResult(4L)
                ;
    }

    // 테스트 성공 예제
    // take를 통해 5개의 데이터만 받기로함
    @Test
    public void assertResultSuccessTest() {
        Observable.interval(200L, TimeUnit.MICROSECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .filter(data -> data >3)
                .test()
                .awaitDone(1100L, TimeUnit.MICROSECONDS)
                .assertResult(4L)
        ;
    }

}
