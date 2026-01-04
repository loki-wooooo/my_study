package io.github.lokiwooooo.demo.part1.setcion10.class03;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class PublisherProbeTestExample01 {
    @Test
    public void publisherProbeTest(){
        PublisherProbe<String> probe = PublisherProbe.of(PublisherProbeExample.useStandbyPower());

        StepVerifier
                .create(PublisherProbeExample.processWith(PublisherProbeExample.useMainPower(), probe.mono()))
                .expectNextCount(1) // emit 데어터는 Standby의 mono여서 1개만 나옴
                .verifyComplete();

        probe.assertWasSubscribed(); // 예상 실행결로 구독이 잘 됐는지
        probe.assertWasRequested(); // 데이터요청이 잘 됐는지
        probe.assertWasNotCancelled(); // 구독 취소가 없었는지
    }
}
