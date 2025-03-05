//package com.zalesia.hellomessagequeue.step5;
//
<<<<<<< Updated upstream
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
=======
//import org.springframework.amqp.core.*;
>>>>>>> Stashed changes
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    // 큐 이름을 정의
//    public static final String ERROR_QUEUE = "error_queue";
//    public static final String WARN_QUEUE = "warn_queue";
//    public static final String INFO_QUEUE = "info_queue";
<<<<<<< Updated upstream
//
//    public static final String DIRECT_EXCHANGE = "direct_exchange";
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange(DIRECT_EXCHANGE);
//    }
//
=======
//    public static final String ALL_LOG_QUEUE = "all_log_queue";
//
//    public static final String DIRECT_EXCHANGE = "direct_exchange";
//    public static final String TOPIC_EXCHANGE = "topic_exchange";
//
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(TOPIC_EXCHANGE);
//    }
//
//    //    @Bean
////    public DirectExchange directExchange() {
////        return new DirectExchange(DIRECT_EXCHANGE);
////    }
//
>>>>>>> Stashed changes
//    @Bean
//    public Queue errorQueue() {
//        return new Queue(ERROR_QUEUE, false);
//    }
//
//    @Bean
//    public Queue warnQueue() {
//        return new Queue(WARN_QUEUE, false);
//    }
//
//    @Bean
//    public Queue infoQueue() {
<<<<<<< Updated upstream
//        return new Queue(DIRECT_EXCHANGE, false);
=======
//        return new Queue(INFO_QUEUE, false);
//    }
//
//    @Bean
//    public Queue allLogQueue() {
//        return new Queue(TOPIC_EXCHANGE, false);
>>>>>>> Stashed changes
//    }
//
//    @Bean
//    public Binding errorBinding() {
<<<<<<< Updated upstream
//        return BindingBuilder.bind(errorQueue()).to(directExchange()).with("error");
=======
//        return BindingBuilder.bind(errorQueue()).to(topicExchange()).with("log.error");
>>>>>>> Stashed changes
//    }
//
//    @Bean
//    public Binding warnBinding() {
<<<<<<< Updated upstream
//        return BindingBuilder.bind(warnQueue()).to(directExchange()).with("warn");
=======
//        return BindingBuilder.bind(warnQueue()).to(topicExchange()).with("log.warn");
>>>>>>> Stashed changes
//    }
//
//    @Bean
//    public Binding infoBinding() {
<<<<<<< Updated upstream
//        return BindingBuilder.bind(infoQueue()).to(directExchange()).with("info");
//    }
//
=======
//        return BindingBuilder.bind(infoQueue()).to(topicExchange()).with("log.info");
//    }
//
//    @Bean
//    public Binding allLogBinding() {
//        return BindingBuilder.bind(allLogQueue()).to(topicExchange()).with("log.*");
//    }
//
//    //    @Bean
////    public Binding errorBinding() {
////        return BindingBuilder.bind(errorQueue()).to(directExchange()).with("error");
////    }
////
////    @Bean
////    public Binding warnBinding() {
////        return BindingBuilder.bind(warnQueue()).to(directExchange()).with("warn");
////    }
////
////    @Bean
////    public Binding infoBinding() {
////        return BindingBuilder.bind(infoQueue()).to(directExchange()).with("info");
////    }
//
>>>>>>> Stashed changes
//}
