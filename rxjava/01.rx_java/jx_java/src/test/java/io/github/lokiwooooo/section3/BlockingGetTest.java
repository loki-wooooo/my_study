package io.github.lokiwooooo.section3;

import io.reactivex.Observable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

// blockingGet을 사용한 통지 데이터 테스트 예제
public class BlockingGetTest {

    @Test
    public void blockingGetEmptyTest() {
        //then
        assertThat(Observable.empty().firstElement().blockingGet(), is(nullValue()));
    }

    // A 지점의 월간 매출 합계 테스트
    @Test
    public void totalSalesOfBranchATest() {
        //when
        int actual = SampleObservable.getSalesOfBranchA()
                // 통지되는 데이터가 연속적으로 2개씩 처리함
                .reduce(0, (a, b) -> a + b)
                .blockingGet();

        //then
        assertThat(actual, is(326_000_000));
    }

    // A, B, C 지점의 연간 매출 합계 테스트
    @Test
    public void salesAllBranchTest() {
        //when
        // zip => 동일한 index의 값을 가공할 수 있음
        int totalSales = Observable.zip(
                        SampleObservable.getSalesOfBranchA()
                        , SampleObservable.getSalesOfBranchB()
                        , SampleObservable.getSalesOfBranchC()
                        , (a, b, c) -> a + b + c
                )
                .reduce((a, b) -> a + b)
                .blockingGet();

        //then
        assertThat(totalSales, is(992_000_000));
    }


}
