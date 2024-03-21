package com.moncoder.lingo.user;

import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class LingoUserApplicationTests {

    @Autowired
    IRedisService<String,Object> redisService;

    @Test
    void testRedisSerializerWrite() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("username","password","phone");
        redisService.set("test",userRegisterDTO);
        System.out.println(userRegisterDTO);
    }

    @Test
    void testRedisSerializerRead(){
        UserRegisterDTO test = (UserRegisterDTO) redisService.get("test");
        System.out.println(test);
    }
}
