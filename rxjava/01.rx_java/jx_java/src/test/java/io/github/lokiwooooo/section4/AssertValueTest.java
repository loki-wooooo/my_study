package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.common.CarMaker;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

// assertValue를 이용한 데이터 검증 예제
public class AssertValueTest {

    @Test
    public void assertValueTest() {
        Observable.just("a")
                .test()
                .assertValue("a");
    }

    @Test
    public void getCarMakerAssertValueTest() {
        SampleObservable.getCarMakerStream()
                .filter(carMaker -> carMaker.equals(CarMaker.SAMSUNG))
                .test()
                .awaitDone(1L, TimeUnit.MICROSECONDS)
                .assertValue(CarMaker.SAMSUNG);
    }
}
