//package com.zalesia.hellomessagequeue.step6;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    //queue&exchange 명칭 정의
//    public static final String ORDER_COMPLETED_QUEUE = "order_completed_queue";
//    public static final String ORDER_EXCHANGE = "order_completed_exchange";
//
//    //dlq&dlx 명칭 정의
//    public static final String DLQ = "deadLetterQueue";
//    public static final String DLX = "deadLetterExchange";
//
//    @Bean
//    public TopicExchange orderExchange() {
//        return new TopicExchange(ORDER_EXCHANGE);
//    }
//
//    // 실패건은 해당 exchange를 라우팅을 탐
//    @Bean
//    public TopicExchange deadLetterExchange() {
//        return new TopicExchange(DLX);
//    }
//
//    // 메시지가 처리되지 못했을 경우 자동으로 DeadLetterQueue 이동시킴
//    @Bean
//    public Queue orderQueue() {
//        return QueueBuilder.durable(ORDER_COMPLETED_QUEUE)
//                .withArgument("x-dead-letter-exchange", DLX)
//                .withArgument("x-dead-letter-routing-key", DLQ)
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
//     *
//     * '#' (해시):
//     * 0개 이상의 단어를 대체합니다2.
//     * 해시 기호 이후의 모든 라우팅 키와 매칭됩니다2.
//     * 예: "fruit.#"는 "fruit.banana", "fruit.apple", "fruit.banana.ripe" 등 "fruit"으로 시작하는 모든 라우팅 키와 일치합니다2.
//     * */
//    @Bean
//    public Binding orderCompletedBinding() {
//        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with("order.completed.#");
//    }
//
//    @Bean
//    public Binding deadLetterQueueBinding() {
//        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DLQ);
//    }
//
//}
