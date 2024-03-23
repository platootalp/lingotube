package com.moncoder.lingo.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoAdminApplication.class, args);
    }

}
