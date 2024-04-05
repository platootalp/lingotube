package com.moncoder.lingo.user;

import com.moncoder.lingo.api.config.DefaultFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.moncoder.lingo.api.client", defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoUserApplication.class, args);
    }

}
