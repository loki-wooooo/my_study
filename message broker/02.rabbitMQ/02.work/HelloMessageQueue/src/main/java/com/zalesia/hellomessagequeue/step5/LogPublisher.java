//package com.zalesia.hellomessagequeue.step5;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LogPublisher {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public LogPublisher(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void publish(final String routingKey, final String message) {
<<<<<<< Updated upstream
//        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, routingKey, message);
//        System.out.println("[#] message published : " + message);
//    }
=======
//        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, routingKey, message);
//        System.out.println("[#] message published : " + message);
//    }
//
////    public void publish(final String routingKey, final String message) {
////        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, routingKey, message);
////        System.out.println("[#] message published : " + message);
////    }
>>>>>>> Stashed changes
//}
