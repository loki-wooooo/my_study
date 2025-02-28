//package com.zalesia.hellomessagequeue.step6;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class OrderConsumer {
//
//    //3번정도 재실행
//    private static final Integer MAX_RETRY_COUNT = 3;
//    //재시도 실행 횟수
//    private static Integer RETRY_COUNT = 0;
//
//    /**
//     * containerFactory -> RabbitMQManualConfig에 선언한 factory를 넣어줌
//     * channel -> 현재 ack/nack 체크 받는 내용이 MANUEL이라 인자값을 받아서 처리해야함
//     * Header -> 메시지의 태그를 달고 다녀야함 (메세지가 갖고 있는 고유 번호를 갖고있음)
//     */
//    @RabbitListener(queues = RabbitMQConfig.ORDER_COMPLETED_QUEUE, containerFactory = "rabbitListenerContainerFactory")
//    public void processOrder(final String message, final Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
//
//        try {
//            // 실패 유발
//            if ("fail".equalsIgnoreCase(message)) {
//                if (RETRY_COUNT < MAX_RETRY_COUNT) {
//                    System.err.println("[#] 실패 & 재처리 시도 횟수 : " + RETRY_COUNT);
//                    RETRY_COUNT++;
//                    throw new RuntimeException(message);
//                } else {
//                    System.err.println("[#] 실패 최대 횟수 초과, DLQ 이동");
//                    RETRY_COUNT = 0;
//                    // tag, muliline (true -> 기존에 받은 내용도 전체 ack, false -> 현재 메시지 기준), requeue -> 기존큐에 다시 넣는지 유/무
//                    channel.basicNack(tag, false, false);
//                }
//            }
//
//            // 성공
//            System.out.println("[#] 성공 : " + message);
//            //재전송을 하지말라고 처리
//            channel.basicAck(tag, false);
//            RETRY_COUNT = 0;
//        } catch (Exception exception) {
//            System.err.println("[#] error :: " + exception.getMessage());
//            try {
//                // 실패시 basicreject 처리 후 메시지를 다시 처리
//                channel.basicReject(tag, true);
//            } catch (IOException ioException) {
//                System.err.println("[#] fila & reject error :: " + ioException.getMessage());
//            }
//        }
//    }
//}
