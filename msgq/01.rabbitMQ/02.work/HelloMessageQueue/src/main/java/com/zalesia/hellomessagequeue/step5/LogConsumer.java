//package com.zalesia.hellomessagequeue.step5;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LogConsumer {
//
//    @RabbitListener(queues = RabbitMQConfig.ERROR_QUEUE)
//    public void consumeError(final String  message) {
//        System.out.println("[#] ERROR message : " + message);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.WARN_QUEUE)
//    public void consumeWarn(final String  message) {
//        System.out.println("[#] WARN message : " + message);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.INFO_QUEUE)
//    public void consumeInfo(final String  message) {
//        System.out.println("[#] INFO message : " + message);
//    }
//}
