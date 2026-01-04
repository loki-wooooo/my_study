package io.github.lokiwooooo.demo.section7.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
* 구독은 거꾸로 올라감
*   subscribeOn이 **Upstream(원본 데이터 소스)**의 스레드까지 변경하는 이유는 리액티브 스트림의 "구독(Subscription) 프로세스의 방향"
*       -> 구독 신호가 아래에서 위로(Downstream -> Upstream) 전달되기 때문
* subscribeOn(), publishOn()이 같이 있다면, publishOn()을 만나기 전 까지의 Upstream Operator 체인은
* subscribeOn() 에서 지정한 쓰레드에서 실행되고, publishOn()을 만날때마다
* publishOn() 아래의 Operator 체인 downstream은 publishOn()에서 지정한 쓰레드로 실행된다.
* */
@Slf4j
public class SchedulerOperatorExample05 {
    public static void main(String[] args) throws InterruptedException{
        //main에서 실행하기 떄문에 publishOn, subscribeOn을 호출하지 않음
        Flux.fromArray(new Integer[]{1,3,5,7}) // main -> 다른 thread도 변경함 (최상위 upstream에서도 다른 thread를 사용)
                .subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("filter: {}", data))
                .publishOn(reactor.core.scheduler.Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("map: {}", data))
                .subscribe(data -> log.info("data: {}", data));

        Thread.sleep(500L);
    }
}
