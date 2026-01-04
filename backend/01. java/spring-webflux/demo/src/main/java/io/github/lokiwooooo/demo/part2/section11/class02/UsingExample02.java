package io.github.lokiwooooo.demo.part2.section11.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * using()의 사용하기 적절한 예제
 * - 파라미터
 * - Callable(함수형 인터페이스): Resource를 Input으로 제공한다.(resource supplier)
 * - Function(함수형 인터페이스): Input으로 전달받은 Resource를 새로 생성한 Publisher로 emit한다.(source supplier)
 * - Consumer(함수형 인터페이스): 사용이 끝난 Resource를 해제한다.(resource cleanup)
 *
 */
@Slf4j
public class UsingExample02 {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\utils\\05.test\\using_example.txt");
        Flux
                .using(
                        () -> Files.lines(path)
                        , Flux::fromStream
                        , Stream::close
                )
                .subscribe(data -> log.info("data: {}", data));
    }
}
