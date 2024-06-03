package com.moncoder.lingo.vocabulary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoVocabularyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoVocabularyApplication.class, args);
    }

}
