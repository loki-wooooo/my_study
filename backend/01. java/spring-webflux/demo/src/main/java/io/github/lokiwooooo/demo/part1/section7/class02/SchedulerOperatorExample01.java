package io.github.lokiwooooo.demo.section7.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
* Sequence의 Operator 체인에서 최초의 쓰레드는 subscrie()가
* 호출되는 Scope에 있는 쓰레드다.
* */
@Slf4j
public class SchedulerOperatorExample01 {
    public static void main(String[] args) {
        //main에서 실행하기 떄문에 publishOn, subscribeOn을 호출하지 않음
        Flux.fromArray(new Integer[]{1,3,5,7})
                .filter(data -> data > 3)
                .map(data -> data * 10)
                .subscribe(data -> log.info("data: {}", data));
    }
}
