package com.moncoder.lingo.common.service.impl;

import com.moncoder.lingo.common.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Moncoder
 * @version 1.0
 * @description Redis操作封装实现类
 * @date 2024/3/21 13:25
 */
public class RedisServiceImpl<K, V> implements IRedisService<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(K key, V value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean delete(K key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Boolean hasKey(K key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void setExpire(K key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    @Override
    public Long getExpire(K key) {
        return redisTemplate.getExpire(key);
    }


}
