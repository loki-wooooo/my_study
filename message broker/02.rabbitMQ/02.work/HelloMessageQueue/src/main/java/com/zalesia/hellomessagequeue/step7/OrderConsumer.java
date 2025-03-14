//package com.zalesia.hellomessagequeue.step7;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.retry.support.RetryTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderConsumer {
//
//    private final RabbitTemplate rabbitTemplate;
//    private final RetryTemplate retryTemplate;
//
//    public OrderConsumer(RabbitTemplate rabbitTemplate, RetryTemplate retryTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.retryTemplate = retryTemplate;
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.ORDER_COMPLETED_QUEUE)
//    public void consume(final String message) {
//        retryTemplate.execute(context -> {
//            try {
//                System.out.println("[#] Received Order Completed Message: " + message + " Retry : " + context.getRetryCount());
//
//                //실패 조건 fail
//                if ("fail".equalsIgnoreCase(message)) {
//                    throw new RuntimeException(message);
//                }
//                System.out.println("[#] Received Order Completed Message Send :  " + message);
//            } catch (Exception e) {
//                if (context.getRetryCount() >= 2) {
//                    rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_TOPIC_DLX, RabbitMQConfig.DEAD_LETTER_ROUTING_KEY, message);
//                } else {
//                    throw e;
//                }
//            }
//            return null;
//        });
//    }
//}
