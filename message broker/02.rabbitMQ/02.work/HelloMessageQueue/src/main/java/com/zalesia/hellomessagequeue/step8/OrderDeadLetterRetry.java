//package com.zalesia.hellomessagequeue.step8;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderDeadLetterRetry {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public OrderDeadLetterRetry(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.DLQ)
//    public void process(String message) {
//        try {
//            if("fail".equalsIgnoreCase(message)) {
//                message = "success";
//                System.out.println("[#] DLQ Message fixed: " + message);
//            } else {
//                //이미 수정된 메시지는 다시 처리하지 않음
//                System.err.println("[#] DLQ Message already fixed. Ignoring: " + message);
//                return;
//            }
//
//            String fixMessage = "success";
//            rabbitTemplate.convertAndSend(
//                    RabbitMQConfig.ORDER_TOPIC_EXCHANGE,
//                    "order.completed",
//                    message
//            );
//            System.out.println("[#] DLQ Message send : " + message);
//        } catch (Exception e) {
//            System.err.println("[#] DLQ Error Message : " + e.getMessage());
//        }
//    }
//}
