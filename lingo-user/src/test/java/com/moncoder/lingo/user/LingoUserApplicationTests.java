package com.moncoder.lingo.user;

import com.moncoder.lingo.common.constant.SystemConstant;
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
        redisService.incr(SystemConstant.LINGO_USER_COUNT,1);
    }

    @Test
    void testRedisSerializerRead() {

    }
}
