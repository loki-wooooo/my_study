//package com.zalesia.hellomessagequeue.step5;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomExceptionHandler {
//
//    private final LogPublisher logPublisher;
//
//    public CustomExceptionHandler(LogPublisher logPublisher) {
//        this.logPublisher = logPublisher;
//    }
//
//    // 에러나 로그 처리
//    public void handleException(final Exception exception) {
//        String message = exception.getMessage();
//
//        String routingKey;
<<<<<<< Updated upstream
//        if (exception instanceof NullPointerException) {
//            routingKey = "error";
//        } else if (exception instanceof IllegalArgumentException) {
//            // 구분을 위해 찍음
//            routingKey = "warn";
//        } else {
//            routingKey = "error";
//        }
=======
//
//        //direct exchange
////        if (exception instanceof NullPointerException) {
////            routingKey = "error";
////        } else if (exception instanceof IllegalArgumentException) {
////            // 구분을 위해 찍음
////            routingKey = "warn";
////        } else {
////            routingKey = "error";
////        }
//
//        //topic exchange
//        if (exception instanceof NullPointerException) {
//            routingKey = "log.error";
//        } else if (exception instanceof IllegalArgumentException) {
//            // 구분을 위해 찍음
//            routingKey = "log.warn";
//        } else {
//            routingKey = "log.error";
//        }
//
>>>>>>> Stashed changes
//        logPublisher.publish(routingKey, "Exception이 발생했음 : " + message);
//    }
//
//    // 메시지 처리
//    public void handleMessage(final String message) {
<<<<<<< Updated upstream
//        String routingKey = "info";
=======
//        String routingKey = "log.info";
>>>>>>> Stashed changes
//        logPublisher.publish(routingKey, "INFO Log : " + message);
//
//    }
//
//}
