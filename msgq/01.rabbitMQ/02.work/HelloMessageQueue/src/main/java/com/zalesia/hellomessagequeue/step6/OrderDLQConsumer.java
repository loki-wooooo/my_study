//package com.zalesia.hellomessagequeue.step6;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderDLQConsumer {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public OrderDLQConsumer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.DLQ)
//    public void process(final String message) {
//        System.out.println("[#] DLQ Message : " + message);
//
//        try {
//            String fixMessage = "success";
//            rabbitTemplate.convertAndSend(
//                    RabbitMQConfig.ORDER_EXCHANGE,
//                    "order.completed.shipping",
//                    fixMessage
//            );
//            System.out.println("[#] DLQ Message send : " + message);
//        } catch (Exception e) {
//            System.err.println("[#] DLQ Error Message : " + e.getMessage());
//        }
//    }
//}
