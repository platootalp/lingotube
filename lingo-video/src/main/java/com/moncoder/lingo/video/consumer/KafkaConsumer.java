package com.moncoder.lingo.video.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moncoder.lingo.entity.VmsVideo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO kafka消费者
 * @date 2024/5/21 16:24
 */
@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "lingo.video", groupId = "lingo.video.consumer")
    public void consumeMessage(ConsumerRecord<String, String> consumerRecord) {
        try {
            VmsVideo video = objectMapper.readValue(consumerRecord.value(), VmsVideo.class);
            log.info("消费者消费topic:{} partition:{}的消息 -> {}",
                    consumerRecord.topic(),
                    consumerRecord.partition(),
                    video.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
