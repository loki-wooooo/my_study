package com.zalesia.hellomessagequeue.step2;

import org.springframework.stereotype.Component;

@Component
public class WorkQueueConsumer {

    // RabbitMQConfig에서 listenerAdapter-> 실제 consumer가 받는 메서드 정의
    public void workQueueTask(final String message) {

        // 메시지를 "|" 이걸 이용해서 파라미터를 프로듀서가 한번에 전송하도록 처리
        String[] messageParts = message.split("\\|");

        String originMessage = messageParts[0];
        Integer duration = Integer.parseInt(messageParts[1].trim());

        System.out.println("# Received: " + originMessage + " (duration: " + duration + " ms)");

        try {
            Integer seconds = duration / 1000;
            for (int i=0; i<seconds; i++) {
                Thread.sleep(1000);// 1초 대기
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n # Completed: " + originMessage);
    }
}
