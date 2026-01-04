package io.github.lokiwooooo.demo.part1.setcion10.class02;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

/*
 * emit 되는 모든 데이터들을 캡처하여 컬렉션에 기록한 후, 기록된 데이터들을 검증하는 예제
 * - recordWith()를 사용하여 emit된 데이터를 기록하는 세션을 시작한다.
 * - thenConsumeWhile()을 사용하여 조건에 맞는 데이터만 소비한다. 여기서 조건에 맞는 데이터들이 ArrayList에 추가(기록) 된다.
 * - expectRecordedMatches()를 사용하여 기록된 데이터의 컬렉션을 검증한다.
 * */
public class StepVerifierRecordTestExample02 {
    @Test
    public void getCountryTest() {
        StepVerifier
                .create(RecordExample.getCountry(Flux.just("france", "russia", "greece", "poland")))
                .expectSubscription() // 구독확인
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty()) //조건에 맞는 데이터만 기록
                .expectRecordedMatches(countries ->
                        countries
                                .stream()
                                .allMatch(country -> Character.isUpperCase(country.charAt(0)))
                )
                .expectComplete()
                .verify();
    }
}
