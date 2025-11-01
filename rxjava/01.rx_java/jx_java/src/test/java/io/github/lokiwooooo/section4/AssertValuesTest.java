package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.common.CarMaker;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

// assertValues를 이용한 데이터 검증 예제
public class AssertValuesTest {

    // awaitDone : awaitDone 함수는 리액티브 프로그래밍에서 생산자를 구독한 소비자가 비동기 작업이 완료될 때까지 현재 스레드를 차단(블로킹)하기 위해 사용
    @Test
    public void getCarMakerAssertValueTest() {
        SampleObservable.getDuplicatedCarMakerStream()
                .filter(carMaker -> carMaker.equals(CarMaker.CHEVROLET))
                .test()
                .awaitDone(1L, TimeUnit.MICROSECONDS) // 생산자에서 구독한 소비자의 스레드에서 실행이 끝날때까지 기다림,
                .assertValues(CarMaker.CHEVROLET, CarMaker.CHEVROLET);
    }
}
