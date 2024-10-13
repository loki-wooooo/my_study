package com.example.catalogservice.messagequeue;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    /**
     * 팩토리&리스너&리스너팩토리
     * ConsumerFactory (컨슈머 팩토리)
     * 목적: 카프카 컨슈머 인스턴스를 생성하는 데 필요한 구성을 제공
     * 기능:
     *  카프카 브로커 주소, 그룹 ID, 키/값 역직렬화기 등의 컨슈머 설정을 정의합니다
     *  이러한 설정을 바탕으로 실제 카프카 컨슈머 인스턴스를 생성합니다.
     *
     * KafkaListenerContainerFactory (컨슈머 리스너 팩토리)
     * 목적: KafkaListener가 동작할 환경(kafkaListenerContainer)을 생성합니다
     * 컨테이너 유형:
     *  KafkaMessageListenerContainer: 싱글 스레드에서 설정된 토픽 또는 파티션의 모든 메시지를 수신합니다.
     *  ConcurrentMessageListenerContainer: 멀티스레드 환경에서 메시지를 병렬로 처리할 수 있게 해줍니다
     * 설정: ConsumerFactory를 사용하여 Kafka 컨슈머 인스턴스를 생성하고, 이를 바탕으로 리스너 컨테이너를 구성합니다1.
     * 병렬 처리: ConcurrentKafkaListenerContainerFactory를 사용하면 동시에 여러 메시지를 처리할 수 있는 컨테이너를 생성할 수 있습니다2.
     * 커스터마이징: 컨테이너의 동작 방식, 예를 들어 커밋 모드, 동시성 레벨 등을 설정할 수 있습니다
     *
     * Listener (리스너)
     * 목적: 실제로 카프카 토픽에서 메시지를 수신하고 처리하는 역할을 합니다
     * 기능:
     *  특정 토픽의 메시지를 구독하고 수신합니다.
     *  수신된 메시지에 대한 비즈니스 로직을 실행합니다.
     * */

    // kafka의 ConsumerFactory 설정
    // 접속 정보 등록
    // Kafka 컨슈머를 설정
    /**
     * BOOTSTRAP_SERVERS_CONFIG: Kafka 브로커의 주소를 설정합니다. 여기서는 로컬호스트의 9092 포트를 사용합니다.
     * GROUP_ID_CONFIG: 컨슈머 그룹 ID를 설정합니다. 같은 그룹 ID를 가진 컨슈머들은 협력하여 토픽의 파티션을 소비합니다.
     * KEY_DESERIALIZER_CLASS_CONFIG 및 VALUE_DESERIALIZER_CLASS_CONFIG: 메시지 키와 값의 역직렬화 클래스를 지정합니다. 여기서는 문자열 형식을 사용합니다.
     * */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> properties = new HashMap<>();

        // 서버의 정보
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroupId");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    // 리스너 등록
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return kafkaListenerContainerFactory;
    }
}
