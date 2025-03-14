//package com.zalesia.hellomessagequeue.step2;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WorkQueueProducer {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public WorkQueueProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void sendWorkQueue(final String workQueueMessage, final Integer duration) throws Exception {
//        String message = workQueueMessage + "|" + duration;
//        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
//        System.out.println("# Send WorkQueue Message: " + message);
//    }
//}
