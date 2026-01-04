package io.github.lokiwooooo.demo.part1.setcion10.class01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

/*
 * onNext signal 을 통해 emit 된 데이터의 개수를 검증하는 예제
 *  - 검증에 실패한 경우에는 StepVerifierOptions에서 지정한 Scanario Name이 사용된다.
 *  */
public class StepVerifierGeneralTestExample06 {

    @Test
    public void sayHelloReactorTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier
                .create(GeneralExample.takeNumber(source, 500L)
                        , StepVerifierOptions.create().scenarioName("Verifier from 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(498) // 다음 emit 데이터 검증x, 다음에 올 데이터의 개수가 정확히 n개인지 를 검증할 때 사용
                .expectNext(499)
                .expectComplete()
                .verify();
    }

}
