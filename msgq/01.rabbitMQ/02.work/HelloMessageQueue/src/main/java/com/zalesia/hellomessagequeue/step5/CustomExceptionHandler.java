package com.zalesia.hellomessagequeue.step5;

import org.springframework.stereotype.Component;

@Component
public class CustomExceptionHandler {

    private final LogPublisher logPublisher;

    public CustomExceptionHandler(LogPublisher logPublisher) {
        this.logPublisher = logPublisher;
    }

    // 에러나 로그 처리
    public void handleException(final Exception exception) {
        String message = exception.getMessage();

        String routingKey;
        if (exception instanceof NullPointerException) {
            routingKey = "error";
        } else if (exception instanceof IllegalArgumentException) {
            // 구분을 위해 찍음
            routingKey = "warn";
        } else {
            routingKey = "error";
        }
        logPublisher.publish(routingKey, "Exception이 발생했음 : " + message);
    }

    // 메시지 처리
    public void handleMessage(final String message) {
        String routingKey = "info";
        logPublisher.publish(routingKey, "INFO Log : " + message);

    }

}
