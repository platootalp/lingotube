package com.moncoder.lingo.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoOssApplication.class, args);
    }

}
