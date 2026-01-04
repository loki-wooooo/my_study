package io.github.lokiwooooo.demo.part1.setcion10.class01;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/*
 * exceptNext()를 사용하여, emit된 n 개의 데이터를 검증하는 예제
 * */
public class StepVerifierGeneralTestExample02 {

    @Test
    public void sayHelloReactorTest() {
        StepVerifier
                .create(GeneralExample.sayHelloReactor())
                .expectSubscription()
                .expectNext("Hello")
                .expectNext("Reactor")
                .expectComplete()
                .verify();
    }

}
