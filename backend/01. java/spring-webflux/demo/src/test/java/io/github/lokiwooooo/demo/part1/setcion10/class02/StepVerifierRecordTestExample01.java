package io.github.lokiwooooo.demo.part1.setcion10.class02;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
 * emit 되는 모든 데이터들을 캡처하여 컬렉션에 기록한 후, 기록된 데이터들을 검증하는 예제
 * - recordWith()를 사용하여 emit된 데이터를 기록하는 세션을 시작한다.
 * - thenConsumeWhile()을 사용하여 조건에 맞는 데이터만 소비한다. 여기서 조건에 맞는 데이터들이 ArrayList에 추가(기록) 된다.
 * - consumeRecordedWith()를 사용하여 기록된 데이터를 소비한다. 여기서는 assertThat()를 사용하여 검증한다.
 * */
public class StepVerifierRecordTestExample01 {
    @Test
    public void getCountryTest() {
        StepVerifier
                .create(RecordExample.getCountry(Flux.just("france", "russia", "greece", "poland")))
                .expectSubscription() // 구독확인
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .consumeRecordedWith(countries -> {
                    assertThat(countries, everyItem(hasLength(6)));
                    assertThat(
                            countries
                                    .stream()
                                    .allMatch(country -> Character.isUpperCase(country.charAt(0)))
                            , is(true)
                    );
                })
                .expectComplete()
                .verify();
    }
}
