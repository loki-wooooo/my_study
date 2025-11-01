package io.github.lokiwooooo.section4;

import io.github.lokiwooooo.common.Car;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

// assertEmpty를 사용하여 해당 시점까지 통지된 데이터가 있는지 검증하는 예제
public class AssertEmptyTest {

    // 테스트 실패 실행 예제
    @Test
    public void getCarStreamEmptyFailTest() {
        // when
        Observable<Car> observable = SampleObservable.getCarStream();
        TestObserver<Car> observer = observable.test(); //test 함수를 이용한 test 실행

        //then
        observer.awaitDone(100L, TimeUnit.MICROSECONDS).assertEmpty();
    }

    // 테스트 성공 예제
    // 특정 시점동한 통지되는 데이터가 없다면 테스트에 성공함
    @Test
    public void getCarStreamEmptySuccessTest() {
        // when
        Observable<Car> observable = SampleObservable.getCarStream();
        TestObserver<Car> observer = observable.delay(1000L, TimeUnit.MILLISECONDS).test();

        //then
        observer.awaitDone(100L, TimeUnit.MICROSECONDS).assertEmpty();
    }
}
