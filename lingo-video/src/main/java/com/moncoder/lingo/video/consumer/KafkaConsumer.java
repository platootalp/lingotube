package com.moncoder.lingo.video.consumer;

import cn.hutool.core.codec.PunyCode;
import com.moncoder.lingo.common.constant.KafkaConstant;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO
 * @date 2024/5/21 16:24
 */
@Component
public class KafkaConsumer {

   @KafkaListener(topics="lingo.video",groupId = "lingo.video.consumer")
   public void listen(String message){
      System.out.println(message);
   }
}
