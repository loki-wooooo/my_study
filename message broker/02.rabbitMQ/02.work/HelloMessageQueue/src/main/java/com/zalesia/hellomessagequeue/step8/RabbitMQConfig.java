//package com.zalesia.hellomessagequeue.step8;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    //queue&exchange 명칭 정의
//    public static final String ORDER_COMPLETED_QUEUE = "orderCompletedQueue";
//    public static final String ORDER_TOPIC_EXCHANGE = "orderCompletedExchange";
//
//    //dlq&dlx 명칭 정의
//    public static final String DLQ = "deadLetterQueue";
//    public static final String ORDER_TOPIC_DLX = "deadLetterExchange";
//    public static final String DEAD_LETTER_ROUTING_KEY = "dead.letter";
//
//    @Bean
//    public TopicExchange orderExchange() {
//        return new TopicExchange(ORDER_TOPIC_EXCHANGE);
//    }
//
//    // 실패건은 해당 exchange를 라우팅을 탐
//    @Bean
//    public TopicExchange deadLetterExchange() {
//        return new TopicExchange(ORDER_TOPIC_DLX);
//    }
//
//    // 메시지가 처리되지 못했을 경우 자동으로 DeadLetterQueue 이동시킴
//    @Bean
//    public Queue orderQueue() {
//        return QueueBuilder.durable(ORDER_COMPLETED_QUEUE)
//                .withArgument("x-dead-letter-exchange", ORDER_TOPIC_DLX)
//                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY)
//                .ttl(5000)
//                .build();
//    }
//
//    @Bean
//    public Queue deadLetterQueue() {
//        return new Queue(DLQ);
//    }
//
//    /**
//     * '*' (와일드카드):
//     * 정확히 한 단어를 대체합니다2.
//     * 예: "fruit.*"는 "fruit.banana"나 "fruit.apple"과 일치하지만, "fruit.banana.ripe"와는 일치하지 않습니다2.
//     * <p>
//     * '#' (해시):
//     * 0개 이상의 단어를 대체합니다2.
//     * 해시 기호 이후의 모든 라우팅 키와 매칭됩니다2.
//     * 예: "fruit.#"는 "fruit.banana", "fruit.apple", "fruit.banana.ripe" 등 "fruit"으로 시작하는 모든 라우팅 키와 일치합니다2.
//     */
//    @Bean
//    public Binding orderCompletedBinding() {
//        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with("order.completed");
//    }
//
//    @Bean
//    public Binding deadLetterQueueBinding() {
//        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING_KEY);
//    }
//
//}
