package io.github.lokiwooooo.demo.section3.class02;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

/*
* Mono의 활용 예제
* - worldtimeapi.org Open API를 써서 서울의 현재시간을 조회한다.
* */
@Slf4j
public class MonoExample03 {
    public static void main(String[] args) {
        URI worldTimeApiUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setAccept(Collections.singletonList(org.springframework.http.MediaType.APPLICATION_JSON));

        Mono.just(
                //mono의 datasource
                restTemplate.exchange(worldTimeApiUri, org.springframework.http.HttpMethod.GET, new org.springframework.http.HttpEntity<>(headers), String.class)
        )
                .map(response -> {
                    DocumentContext jsonContext = JsonPath.parse(response.getBody());
                    String dateTime = jsonContext.read("$.datetime");
                    return dateTime;
                })
                .subscribe(
                        data -> log.info("# emitted data: {}", data)
                        , error -> {
                            log.error("# error: {}", error.getMessage());
                        }
                        , () -> log.info("# emitted onComplated signal")
                );
    }
}
