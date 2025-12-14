package io.github.lokiwooooo.section3;

import io.github.lokiwooooo.common.Car;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//BlockingFirstTest를 사용한 통지된 마지막 데이터를 테스트하는 예제
public class BlockingLastTest {

    // car 리스트 중 마지막 Car 테스트
    @Test
    public void getCarStreamLastTest() {
        //when
        Car car = SampleObservable.getCarStream().blockingLast();
        String actual = car.getCarName();

        //then
        assertThat(actual, is("SM5"));
    }

    @Test
    public void getSalesOfBranchALastTest() {
        //when
        int actual = SampleObservable.getSalesOfBranchA()
                .take(6)
                .blockingLast();

        //then
        assertThat(actual, is(40_000_000));
    }

}
