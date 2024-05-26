package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {

//        // Custom Pre Filter
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Global Filter BaseMessage :: {} ", config.getBaseMessage());
//
//            if (config.getPreLogger()) {
//                log.info("Global Filter Start :: request id {} ", request.getId());
//            }
//
//            // Custom Post Filter
//            // Mono -> 동기에서 비동기로 변경 webflux
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                if (config.getPostLogger()) {
//                    log.info("Global Filter End :: response code {} ", response.getStatusCode());
//                }
//            }));
//        };

        //OrderedGatewayFilter gatewayfilter를 구현하는 자식 클래스
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Filter BaseMessage :: {} ", config.getBaseMessage());
            if (config.getPreLogger()) {
                log.info("Logging Pre Filter :: request id {} ", request.getId());
            }
            // Custom Post Filter
            // Mono -> 동기에서 비동기로 변경 webflux
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.getPostLogger()) {
                    log.info("Logging Post Filter :: response code {} ", response.getStatusCode());
                }
            }));
        }, Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }

    //inner class 정의
    // filter 안에서 정의가 자유롭게 가능
    @Data
    public static class Config {
        // Put the configuration properties
        private String baseMessage;
        private Boolean preLogger;
        private Boolean postLogger;
    }
}
