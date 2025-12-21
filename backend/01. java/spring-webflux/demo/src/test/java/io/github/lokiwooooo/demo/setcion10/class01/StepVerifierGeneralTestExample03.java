package io.github.lokiwooooo.demo.setcion10.class01;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/*
 * - verifierComplete()를 사용하여 검증 실행 및 기대값으로 onComplete signal이 맞는지 검증하는 예제
 *  - as(description)을 사용해서 실패한 expectXXX() 에게 description을 저정 할 수 있다.
 * */
public class StepVerifierGeneralTestExample03 {

    @Test
    public void sayHelloReactorTest() {
        StepVerifier
                .create(GeneralExample.sayHelloReactor())
                .expectSubscription()
                .as("# expect subscription")
                .expectNext("Hi")
                .as("# expect Hi")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }

}
