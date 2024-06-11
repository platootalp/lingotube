package com.moncoder.lingo.vocabulary;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAdminServer
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoVocabularyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoVocabularyApplication.class, args);
    }

}
