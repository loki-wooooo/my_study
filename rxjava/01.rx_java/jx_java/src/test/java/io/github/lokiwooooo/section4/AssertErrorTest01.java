package io.github.lokiwooooo.section4;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

// assertError를 이용하여 데이터 통지 중에 에러가 발생했는지를 검증하는 예제
public class AssertErrorTest01 {

    // 단순히 예외가 발생하였는지를 테스트하는 예제
    @Test
    public void assertErrorTest01() {
        Observable.interval(100L, TimeUnit.MICROSECONDS)
                .map(data -> {
                    long value;
                    if (data == 4) {
                        value = data / 0;
                    } else {
                        value = data / 2;
                    }
                    return value;
                })
                .test()
                .awaitDone(1000L, TimeUnit.MICROSECONDS)
                .assertError(Throwable.class);
    }

    // 구체적인 예외 클래스를 비교 테스트하는 예제
    @Test
    public void assertErrorTest02() {
        Observable.interval(100L, TimeUnit.MICROSECONDS)
                .map(data -> {
                    long value;
                    if (data == 4) {
                        value = data / 0;
                    } else {
                        value = data / 2;
                    }
                    return value;
                })
                .test()
                .awaitDone(1000L, TimeUnit.MICROSECONDS)
                .assertError(error -> error.getClass() == ArithmeticException.class);

    }


}
