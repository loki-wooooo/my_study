package com.zalesia.hellomessagequeue.step10;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class MessageConsumer {

    private final StockRepository stockRepository;

    public MessageConsumer(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(StockEntity stockEntity, @Header(AmqpHeaders.DELIVERY_TAG) long tag, Channel channel) {

        try {
            System.out.println("[Consumer] " + stockEntity);
            Thread.sleep(200);

            //Local To Local 이여서 해당 값이 없다고 나옴
            Optional<StockEntity> optionalStockEntity = stockRepository.findById(stockEntity.getId());
            if (optionalStockEntity.isPresent()) {
                StockEntity stockEntityData = optionalStockEntity.get();
                stockEntityData.setUpdatedAt(LocalDateTime.now());
                StockEntity save = stockRepository.save(stockEntityData);
                System.out.println("[Save Entity Consumer] " + save);
            } else {
                throw new RuntimeException("Stock Entity not found");
            }
            channel.basicAck(tag, false);

        } catch (Exception e) {
            System.err.println("[Consumer Error] " + e.getMessage());
            try {
                channel.basicNack(tag, false, false);
            } catch (IOException ioException) {
                System.err.println("[Consumer send nack]" + ioException.getMessage());
            }
        }


    }
}
