package com.moncoder.lingo.video;

import com.moncoder.lingo.video.config.DefaultFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableFeignClients(defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoVideoApplication.class, args);
    }

}
