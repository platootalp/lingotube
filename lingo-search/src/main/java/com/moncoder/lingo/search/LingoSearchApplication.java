package com.moncoder.lingo.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoSearchApplication.class, args);
    }

}
