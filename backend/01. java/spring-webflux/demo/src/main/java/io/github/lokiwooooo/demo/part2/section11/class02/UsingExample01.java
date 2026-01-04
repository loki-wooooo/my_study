package io.github.lokiwooooo.demo.part2.section11.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * using()의 개념 이해 예제
 * - 파라미터
 * - Callable(함수형 인터페이스): Resource를 Input으로 제공한다.(resource supplier)
 * - Function(함수형 인터페이스): Input으로 전달받은 Resource를 새로 생성한 Publisher로 emit한다.(source supplier)
 * - Consumer(함수형 인터페이스): 사용이 끝난 Resource를 해제한다.(resource cleanup)
 *
 */
@Slf4j
public class UsingExample01 {
    public static void main(String[] args) {
        Mono
                .using(
                        () -> "Resource" // 문자열 전달
                        , resource -> Mono.just(resource) // 해당 resource 전달을 downstream으로 emit
                        , resource -> log.info("cleanup: {}", resource) // 사용이 끝난 resource 해제 -> 여기서는 문자열 해제
                )
                .subscribe(data -> log.info("data: {}", data));
    }
}
