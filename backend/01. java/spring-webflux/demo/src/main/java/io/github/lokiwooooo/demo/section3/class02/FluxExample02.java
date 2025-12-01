package io.github.lokiwooooo.demo.section3.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
 * Flux 에서의 Operator 채인 사용 예제
 * */
@Slf4j
public class FluxExample02 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
                .filter(num -> num > 6) //map으로 다운스트림 emit
                .map(num -> num * 2)
                .subscribe(data -> log.info("data: {}", data));
    }
}
