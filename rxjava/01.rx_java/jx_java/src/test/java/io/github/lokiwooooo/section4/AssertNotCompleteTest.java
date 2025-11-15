package io.github.lokiwooooo.section4;


import io.github.lokiwooooo.utils.TimeUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

// assertComplate를 이용하여 A 지점과 B 지점의 매출 합계 처리가 지정된 시간안에 끝나는지 검증하는 예제
public class AssertNotCompleteTest {

    @Test
    public void assertCompleteTest() {
        SampleObservable.getSalesOfBranchA()
                .zipWith( // getSalesOfBranchA, getSalesOfBranchB 데이터를 합쳐서 통계함
                        SampleObservable.getSalesOfBranchB(),
                        (a, b) -> {
                            TimeUtil.sleep(1000L);
                            return a + b;
                        }
                )
                .test()
                .awaitDone(3000L, TimeUnit.MICROSECONDS)
                .assertNotComplete();
    }
}
