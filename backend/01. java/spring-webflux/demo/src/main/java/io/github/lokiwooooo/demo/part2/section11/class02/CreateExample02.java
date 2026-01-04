package io.github.lokiwooooo.demo.part2.section11.class02;


import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * create 개념 이해 예제
 *  - Subscriber의 request와 상관없이 next signal 이벤트를 발생하는 예제
 *
 */
@Slf4j
public class CreateExample02 {
    // Downstream 쪽에서 데이터를 요청하지 않아도 create 오퍼레이터 내부에서 데이터를 emit하는 예제 코드
    public static void main(String[] args) throws InterruptedException {
        log.info("# start");

        // 변동가격을 emit하는 역할
        CryptoCurrencyPriceEmitter priceEmitter = new CryptoCurrencyPriceEmitter();

        Flux.create((FluxSink<Integer> sink) -> {
                    priceEmitter.setListener(new CryptoCurrencyPriceListener() {
                        @Override
                        public void onPrice(List<Integer> priceList) {
                            priceList.stream().forEach(price -> {
                                sink.next(price);
                            });
                        }

                        @Override
                        public void onComplete() {
                            sink.complete();
                        }
                    });
                })
                .publishOn(Schedulers.parallel()) // 데이터 emit, 처리 thread 분리
                .subscribe(
                        data -> log.info(data.toString()),
                        error -> {},
                        () -> log.info("# onComplete"));

        Thread.sleep(3000L);

        priceEmitter.flowInto();

        Thread.sleep(2000L);
        priceEmitter.complete();

        Thread.sleep(100L);
    }
}