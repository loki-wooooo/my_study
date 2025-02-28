package com.zalesia.hellomessagequeue.step10;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "transactionQueue";
    public static final String EXCHANGE_NAME = "transactionExchange";
    public static final String ROUTING_KEY = "transactionRoutingKey";


    @Bean
    public Queue transactionQueue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", "deadLetterQueue")
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("deadLetterQueue");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding transactionBinding(final Queue transactionQueue, final DirectExchange transactionExchange) {
        return BindingBuilder.bind(transactionQueue).to(transactionExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        //entity dto -> converter 사용
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter()); //JSON 활성
        rabbitTemplate.setMandatory(true); //returnCallBack 활성화

        /**
         * confirmCallBack 설정
         * message 가 exchange에 제대로 도착 했는지에 대한 여부
         * ack -> exchange 도달 O, nack -> exchange 도달 X
         *
         * "correlationData"는 메시지 발행자(producer)가 메시지를 추적하고 확인하는 데 사용되는 중요한 메타데이터
         * */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                // 실제 컨펌이 됨
                System.out.println("[Message Confirmed] " + (correlationData != null ? correlationData.getId() : "null"));
            } else {
                // 전송이 안됨을 감지
                System.out.println("[Message Not Confirmed] " + (correlationData != null ? correlationData.getId() : "null") + ", reason : " + cause);

                // 실제 메시지가 처리 실패 했을 경우 처리 로직을 작성 (예, 로그 기록, db 저장, 알림)
            }
        });

        /**
         * returnCallBack 설정
         * Exchange가 Queue로 Routing을 하지 못했을 때 여부
         * */
        rabbitTemplate.setReturnsCallback(returned -> {
            System.out.println("[Return Message] " + returned.getMessage().getBody());
            System.out.println("[Return Exchange] " + returned.getExchange());
            System.out.println("[Return RoutingKey] " + returned.getRoutingKey());

            //deadletter 사용시 여기에서 작성
        });

        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 수정 모드 설정이 들어가야 한다.
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

}
