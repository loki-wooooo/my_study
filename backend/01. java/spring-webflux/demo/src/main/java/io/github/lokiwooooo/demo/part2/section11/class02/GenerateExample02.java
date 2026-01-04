package io.github.lokiwooooo.demo.part2.section11.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

/**
 * generate 개념 예제
 * - 파라미터
 * - Callable(함수형 인터페이스): 초기 상태 값 또는 객체를 제공한다.(State Supplier)
 * - BiFunction<S, T, S>(함수형 인터페이스): synchronousSink 와 현재 상태(State)를 사용하여 Single Signal을 생성한다.
 * 첫 번째 S: 첫 번째 입력 파라미터의 타입
 * 두 번째 T: 두 번째 입력 파라미터의 타입
 * 세 번째 S: 최종 결과값(Return)의 타입
 * - Consumer(함수형 인터페이스): Generator 종료 또는 Subscriber의 구독 취소 시, 호출 되어 후처리 작업을 한다.(State Consumer)
 *
 *
 */
@Slf4j
public class GenerateExample02 {
    public static void main(String[] args) {
        Flux
                // primitive type이 아닌 객체를 넣어서 사용도 가능함
                .generate(() -> Tuples.of(1, 2), (state, sink) -> {
                    sink.next(state.getT1() + " * " + state.getT2() + " = " + state.getT1() * state.getT2()); // data를 emit 처리함
                    if (state.getT2() == 9) {
                        sink.complete();
                    }
                    return Tuples.of(state.getT1(), state.getT2() + 1);
                })
                .subscribe(data -> log.info("구구단  data: {}", data));
    }
}
