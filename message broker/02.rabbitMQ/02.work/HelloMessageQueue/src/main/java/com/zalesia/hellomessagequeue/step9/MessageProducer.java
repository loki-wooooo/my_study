//package com.zalesia.hellomessagequeue.step9;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@Component
//public class MessageProducer {
//
//    private final StockRepository stockRepository;
//    private final RabbitTemplate rabbitTemplate;
//
//    public MessageProducer(StockRepository stockRepository, RabbitTemplate rabbitTemplate) {
//        this.stockRepository = stockRepository;
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Transactional
//    public void sendMessage(final StockEntity stockEntity, final String testCase) {
//        rabbitTemplate.execute(channel -> {
//            try {
//                channel.txSelect(); //트랜잭션이 시작
//                stockEntity.setProcessed(false);
//                stockEntity.setCreatedAt(LocalDateTime.now());
//                StockEntity savedStockEntity = stockRepository.save(stockEntity);
//
//                System.out.println("Saved stock: " + savedStockEntity);
//
//                //메시지 발행
//                rabbitTemplate.convertAndSend("transactionQueue", savedStockEntity);
//
//                if ("fail".equalsIgnoreCase(testCase)) {
//                    throw new RuntimeException("트랜잭션 작업중에 에러 발생");
//                }
//                channel.txCommit();
//                System.out.println("트랜잭션이 정상 처리 되었습니다.");
//
//            } catch (Exception exception) {
//                System.err.println("트랜잭션 실패: " + exception.getMessage());
//                channel.txRollback();
//                System.err.println("트랜잭션 롤백 완료");
//            } finally {
//                if (channel != null) {
//                    try{
//                        channel.close();
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return null;
//        });
//
//    }
//}
