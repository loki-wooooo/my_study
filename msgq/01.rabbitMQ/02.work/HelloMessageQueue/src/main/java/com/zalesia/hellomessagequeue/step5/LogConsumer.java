//package com.zalesia.hellomessagequeue.step5;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LogConsumer {
//
//    @RabbitListener(queues = RabbitMQConfig.ERROR_QUEUE)
<<<<<<< Updated upstream
//    public void consumeError(final String  message) {
=======
//    public void consumeError(final String message) {
>>>>>>> Stashed changes
//        System.out.println("[#] ERROR message : " + message);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.WARN_QUEUE)
<<<<<<< Updated upstream
//    public void consumeWarn(final String  message) {
=======
//    public void consumeWarn(final String message) {
>>>>>>> Stashed changes
//        System.out.println("[#] WARN message : " + message);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.INFO_QUEUE)
<<<<<<< Updated upstream
//    public void consumeInfo(final String  message) {
//        System.out.println("[#] INFO message : " + message);
//    }
=======
//    public void consumeInfo(final String message) {
//        System.out.println("[#] INFO message : " + message);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.ALL_LOG_QUEUE)
//    public void consumeAllLog(final String message) {
//        System.out.println("[#] ALL LOG message : " + message);
//    }
>>>>>>> Stashed changes
//}
