//package com.zalesia.hellomessagequeue.step3;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.management.Notification;
//
//@Component
//public class NotificationPublisher {
//
//    public final RabbitTemplate rabbitTemplate;
//
//    public NotificationPublisher(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void publish(final String message) {
//        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message);
//        System.out.println("[#] Published Notification: " + message);
//    }
//}
