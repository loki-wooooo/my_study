package io.github.lokiwooooo.demo.part1.section8.class03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.awt.print.Book;

/**
 * Context 활용 예제
 * - 직교성을 가지는 정보를 표현할 때 주로 사용한다.
 * 직교성 -> 어플리케이션 실행에 영향을 주지않는 정보(ex. security token), 두 대상이 서로 독립적이고 관련성이 없음
 *
 */
@Slf4j
public class ContextRealExample01 {
    public static void main(String[] args) {
        Mono<String> mono = postBook(Mono.just(
                new Book()
        ))
                .contextWrite(contextView -> contextView.put("TOKEN", "TOKEN_VALUE"));

        mono.subscribe(data -> log.info("data: {}", data));
    }

    private static Mono<String> postBook(Mono<Book> book) {
        // zip-> Tuple의 객체로 합침
        return Mono.zip(book, Mono.deferContextual(contextView -> Mono.just(contextView.get("TOKEN"))))
                .flatMap(tuple -> Mono.just(tuple)) // 외부 API 서버로 HTTP Post request로 전송한다고 가정
                .flatMap(tuple -> {
                    String response = "POST the book(" + tuple.getT1().getNumberOfPages() + " pages) to the server";
                    log.info("response: {}", response);
                    return Mono.just(response); //http response 수신했다고 가정
                });
    }
}
