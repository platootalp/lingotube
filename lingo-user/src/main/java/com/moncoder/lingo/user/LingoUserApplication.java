package com.moncoder.lingo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoUserApplication.class, args);
    }

}
