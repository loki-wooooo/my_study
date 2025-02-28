//package com.zalesia.hellomessagequeue.step8;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderConsumer {
//
//    private static Integer retryCount = 0;
//
//    @RabbitListener(queues = RabbitMQConfig.ORDER_COMPLETED_QUEUE)
//    public void processMessage(final String message) {
//        System.out.println("[#] Received Order Completed Message: " + message + "count: " + retryCount++);
//        if ("fail".equalsIgnoreCase(message)) {
//            throw new RuntimeException(message);
//        }
//        System.out.println("[#] Received Order Completed Message Successfully: " + message);
//    }
//
//}
