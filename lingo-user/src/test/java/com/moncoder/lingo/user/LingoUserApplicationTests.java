package com.moncoder.lingo.user;

import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LingoUserApplicationTests {

    @Autowired
    IRedisService redisService;

    @Test
    void testRedisSerializerWrite() {

    }

    @Test
    void testRedisSerializerRead() {

    }
}
