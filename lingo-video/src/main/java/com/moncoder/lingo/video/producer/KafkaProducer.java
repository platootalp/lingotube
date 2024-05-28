package com.moncoder.lingo.video.producer;

import com.moncoder.lingo.common.constant.KafkaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO
 * @date 2024/5/21 16:04
 */
@Component
public class KafkaProducer {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(KafkaConstant.VIDEO_TOPIC, message).addCallback(
                new ListenableFutureCallback<>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        ex.printStackTrace();
                    }
                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        System.out.println("成功");
                    }
                }
        );
    }
}
