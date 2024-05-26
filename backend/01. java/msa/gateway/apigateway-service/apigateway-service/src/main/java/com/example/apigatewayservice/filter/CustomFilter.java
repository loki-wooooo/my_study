package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

//rx java에서 사용하는 문법
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    //구현을 메서드


    @Override
    public GatewayFilter apply(final Config config) {

        // Custom Pre Filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre Filter :: request id -> {} ", request.getId());

            // Custom Post Filter
            // Mono -> 동기에서 비동기로 변경 webflux
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom Post Filter :: response code -> {} ", response.getStatusCode());
            }));
        };
    }

    //inner class 정의
    public static class Config {
        // Put the configuration properties
    }
}
