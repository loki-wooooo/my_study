//package com.zalesia.hellomessagequeue.step9;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class MessageConsumer {
//
//    private final StockRepository stockRepository;
//
//    public MessageConsumer(StockRepository stockRepository) {
//        this.stockRepository = stockRepository;
//    }
//
//    @RabbitListener(queues = "transactionQueue")
//    public void receiveMessage(StockEntity stockEntity) {
//        System.out.println("Received stock entity: " + stockEntity);
//
//        try {
//            stockEntity.setProcessed(true);
//            stockEntity.setUpdatedAt(LocalDateTime.now());
//            stockRepository.save(stockEntity); //상태 업데이트
//            System.out.println("Successfully processed stock entity: " + stockEntity);
//
//        } catch (Exception e) {
//            System.err.println("stock entity error: " + e.getMessage());
//            throw e; //todo 메시지를 데드레터큐에 집어 넣는다.
//
//        }
//    }
//}
