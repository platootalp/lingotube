package com.moncoder.lingo.vocabulary;

import com.moncoder.lingo.vocabulary.service.IWordService;
import com.moncoder.lingo.vocabulary.util.WordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class LingoVocabularyApplicationTests {

    @Autowired
    private IWordService wordService;

    @Test
    void contextLoads() throws IOException {
        long post = System.currentTimeMillis();
        wordService.importList("test.txt");
        long after = System.currentTimeMillis();
        System.out.println((after - post)/1000 +"s");
    }

}
