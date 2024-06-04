package com.moncoder.lingo.vocabulary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoVocabularyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoVocabularyApplication.class, args);
    }

}
