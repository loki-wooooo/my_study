package com.example.catalogservice.messagequeue;

// 리스너를 통해 데어티를 갖고와서 업데이트를 하는 코드

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {

    CatalogRepository catalogRepository;


    @Autowired
    public KafkaConsumer(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    // 리스너 등록 및 연결
    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage) throws Exception {
        log.info("Kafka Message: -> {}", kafkaMessage);

        //역직렬화로 사용함
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            map = objectMapper.readValue(kafkaMessage, new TypeReference<HashMap<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        CatalogEntity entity = catalogRepository.findByProductId(map.get("productId").toString());
        if (entity != null) {
            //원래 수량에서 전달된 수량을 뺌
            entity.setStock(entity.getStock() - (Integer) map.get("stock"));
            catalogRepository.save(entity);
        }

    }

}
