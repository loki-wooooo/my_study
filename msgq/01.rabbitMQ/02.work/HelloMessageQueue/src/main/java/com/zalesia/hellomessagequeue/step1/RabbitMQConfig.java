package com.zalesia.hellomessagequeue.step1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //Queue name 설정
    public static final String QUEUE_NAME = "hello-queue";

    // RabbitMQ에서 사용할 큐를 정의
    @Bean
    public Queue queue() {
        //durable -> 휘발성이냐 아니냐
        // false -> 휘발성 , true -> 영속성
        return new Queue(QUEUE_NAME, false);
    }

    /**
     * * jdbctemplate 이랑 동일함
     * 메시지 발행(publishing)과 수신에 사용됩니다.
     * */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    /**
     * * 객체 생성 후 컨테이너에 몇가지 속성을 셋팅해야함
     * 이 컨테이너는 메시지 리스너의 생명주기를 관리합니다.
     * ConnectionFactory를 설정하여 RabbitMQ와의 연결을 관리합니다.
     * 리스닝할 큐의 이름을 설정합니다 (QUEUE_NAME).
     * MessageListenerAdapter를 설정하여 실제 메시지 처리를 담당할 리스너를 지정합니다.
     * */
    @Bean
    public SimpleMessageListenerContainer container(
            final ConnectionFactory connectionFactory
            , final MessageListenerAdapter listenerAdapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    /**
     * * 특정 클래스 특정 메서드로 전달하는 어댑터 역
     * 이 어댑터는 수신된 메시지를 지정된 POJO(Plain Old Java Object)의 메서드로 전달합니다.
     * Receiver 객체의 receiveMessage 메서드가 메시지를 처리하도록 설정됩니다.
     * 이를 통해 비즈니스 로직과 메시징 인프라를 분리할 수 있습니다.
     * */
    @Bean
    public MessageListenerAdapter listenerAdapter(final Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


}
