//package com.zalesia.hellomessagequeue.step6;
//
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * RabbitMQ 리스너의 설정을 커스터마이징 한다.
// */
//@Configuration
//@EnableRabbit
//public class RabbitMQManualConfig {
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        // 수정 모드 설정이 들어가야 한다.
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return factory;
//    }
//}
