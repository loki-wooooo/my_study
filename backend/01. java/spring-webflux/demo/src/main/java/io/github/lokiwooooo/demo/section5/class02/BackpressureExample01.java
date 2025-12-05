package io.github.lokiwooooo.demo.section5.class02;

import io.github.lokiwooooo.demo.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

//subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure예제
@Slf4j
public class BackpressureExample01 {
    public static void main(String[] args) {
        // 1~5 다운스트림
        Flux.range(1, 5)
                .doOnNext(System.out::println) // 업스트림 에밋한 데이터 로그 출력
                .doOnRequest(System.out::println) // subscriber에서 요청한 데이터 개수 출력
                .subscribe(new BaseSubscriber<Integer>() {
                    //BaseSubscriber 데이터 요청갯수 조절
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        // 데이터 요청갯수 지정
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        //publisher에서 EMIT한 데이터 처리
                        TimeUtil.sleep(2000L);
                        System.out.println(value);
                        request(1);
                    }
                });
    }

}
