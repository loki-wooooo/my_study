package io.github.lokiwooooo.section3;

import io.github.lokiwooooo.common.CarMaker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

// RxJava의 API를 사용하지 않은 Unit Test 예제
public class UnitTestNotByRxJava {

    @Test
    public void getCarMakerStreamSyncTest() {
        List<CarMaker> carMakerList = new ArrayList<>();
        SampleObservable.getCarMakerStream()
                .subscribe(data -> carMakerList.add(data));
        assertThat(carMakerList.size(), is(5));
    }
}
