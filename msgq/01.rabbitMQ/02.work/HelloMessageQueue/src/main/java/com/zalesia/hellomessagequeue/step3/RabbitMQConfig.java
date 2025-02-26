package com.zalesia.hellomessagequeue.step3;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * Producer    Exchange    Queue    Consumer
 *    |            |         |         |
 *    |     -------|         |         |
 *    |---->|      |-------->|-------->|
 *    |     |      |         |         |
 *    |     -------|         |         |
 *         ^       ^
 *         |       |
 *       Binding  Routing Key
 *
 */
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "notificationQueue";
    public static final String FANOUT_EXCHANGE = "notificationFanoutExchange";

    @Bean
    public Queue notificationQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    /**
     * RabbitMQ에서 Exchange의 역할은 다음과 같습니다:
     * 메시지 라우팅: Exchange는 Producer로부터 받은 메시지를 적절한 Queue로 라우팅합니다.
     * 라우팅 로직 정의: Exchange는 메시지를 어떤 Queue로 보낼지 결정하는 라우팅 규칙을 정의합니다.
     * 메시지 분류: Exchange는 특정 기준에 따라 메시지를 분류하여 필요한 Queue에 전달합니다.
     * Producer와 Queue 간의 중개자: Producer는 직접 Queue에 메시지를 보내지 않고, Exchange를 통해 메시지를 전달합니다.
     * 유연한 메시지 전달: Exchange 타입(Direct, Topic, Headers, Fanout)에 따라 다양한 방식으로 메시지를 Queue에 전달할 수 있습니다.
     * */
    @Bean
    public FanoutExchange notificationFanoutExchange() {
        // 메시지를 수신하면, 연결된 모든 큐로 브로드케스트
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, FanoutExchange notificationFanoutExchange) {
        //BindingBuilder.bind().to()를 통 큐와 익스체인지를 연결함
        return BindingBuilder.bind(notificationQueue).to(notificationFanoutExchange);
    }

}
