package com.zalesia.hellomessagequeue.step10;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class MessageProducer {

    private final StockRepository stockRepository;
    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(StockRepository stockRepository, RabbitTemplate rabbitTemplate) {
        this.stockRepository = stockRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public void sendMessage(final StockEntity stockEntity, final Boolean testCase) {
        stockEntity.setProcessed(true);
        stockEntity.setCreatedAt(LocalDateTime.now());
        StockEntity save = stockRepository.save(stockEntity);
        System.out.println(
                "[producer entity] " + save
        );

        if (stockEntity.getUserId() == null || stockEntity.getUserId().isEmpty()) {
            throw new RuntimeException("User id is null or empty");
        }

        try {
            //메시지를 rabbitmq에 전송 (publisher 컨펌에서 주로 사용하는 객체)
            //메시지 상태, 전송 상태 추척
            CorrelationData correlationData = new CorrelationData(save.getId().toString());
            rabbitTemplate.convertAndSend(
                    testCase ? "nonExistentExchange" : RabbitMQConfig.EXCHANGE_NAME,
                    testCase ? "invalidRoutingKey" : RabbitMQConfig.ROUTING_KEY,
                    save,
                    correlationData
            );

            //correlationData가 5초안에 들어오면 성공
            if(correlationData.getFuture().get(5, TimeUnit.SECONDS).isAck()) {
                System.out.println("[producer correlationData] 성공 " + save);
                save.setProcessed(true);
                stockRepository.save(save);
            } else {
                throw new RuntimeException("# confirm 실패 - 롤백");
            }

        } catch (Exception e) {
            System.err.println("[producer exception] " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
