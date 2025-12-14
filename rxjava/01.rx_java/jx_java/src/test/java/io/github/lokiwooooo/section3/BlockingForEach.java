package io.github.lokiwooooo.section3;

import org.junit.Test;

// blockingforeach를 사용해 통지된 데이터 전부를 테스트 한다.
public class BlockingForEach {

    // A 구간의 속도 중에서 110 이상인 속도만 필터링이 되었는지 테스트
    @Test
    public void getSpeedOfSectionAForEachTest() {
        SampleObservable.getSpeedOfSectionA()
                .filter(speed -> speed > 110)
                .blockingForEach(speed -> System.out.println(speed)
                        //assertThat(speed, greaterThan(110))
                );
    }
}
