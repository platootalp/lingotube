package com.moncoder.lingo.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoUploadApplication.class, args);
    }

}
