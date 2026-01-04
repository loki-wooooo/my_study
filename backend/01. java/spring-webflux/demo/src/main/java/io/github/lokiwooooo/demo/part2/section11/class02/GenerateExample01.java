package io.github.lokiwooooo.demo.part2.section11.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * generate 개념 예제
 * - 파라미터
 * - Callable(함수형 인터페이스): 초기 상태 값 또는 객체를 제공한다.(State Supplier)
 * - BiFunction<S, T, S>(함수형 인터페이스): synchronousSink 와 현재 상태(State)를 사용하여 Single Signal을 생성한다.
 * 첫 번째 S: 첫 번째 입력 파라미터의 타입
 * 두 번째 T: 두 번째 입력 파라미터의 타입
 * 세 번째 S: 최종 결과값(Return)의 타입
 *
 *
 */
@Slf4j
public class GenerateExample01 {
    public static void main(String[] args) {
        Flux
                // () -> 0  초기값, 0 -> state로 전달, sink -> synchronousSink
                .generate(() -> 0, (state, sink) -> {
                    sink.next(state); // data를 emit 처리함
                    if (state == 10) {
                        sink.complete();
                    }
                    return ++state;
                })
                .subscribe(data -> log.info("data: {}", data));
    }
}
