package io.github.lokiwooooo.demo.part1.setcion10.class01;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/*
 * 1개 이상의 emit 된 데이터를 한번에 검증
 * */
public class StepVerifierGeneralTestExample05 {

    @Test
    public void sayHelloReactorTest() {
        Flux<Integer> source = Flux.just(2,4,6,8,10);
        StepVerifier
                .create(GeneralExample.deviceByTwo(source))
                .expectSubscription() //Publisher를 구독했을 때, 구독(Subscription) 이벤트가 정상적으로 발생하는지 검증
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

}
