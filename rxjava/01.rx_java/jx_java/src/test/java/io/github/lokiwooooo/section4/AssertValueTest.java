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
                .awaitDone(1L, TimeUnit.MICROSECONDS) // 생산자에서 구독한 소비자의 스레드에서 실행이 끝날때까지 기다림,
                .assertValue(CarMaker.SAMSUNG);
    }
}
