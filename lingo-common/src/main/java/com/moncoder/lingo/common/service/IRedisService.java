package com.moncoder.lingo.common.service;

import java.util.concurrent.TimeUnit;

/**
 * @author Moncoder
 * @version 1.0
 * @description Redis操作接口
 * @date 2024/3/21 13:12
 */
public interface IRedisService<K,V> {


    /**********string操作**********/

    /**
     * 保存键值对
     * @param key
     * @param value
     */
    void set(K key, V value);

    /**
     * 保存键值对（带过期时间）
     * @param key
     * @param value
     * @param time （单位：秒）
     */
    void set(K key, V value, long time);

    /**
     * 获取值
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 删除键
     * @param key
     */
    Boolean delete(K key);

    /**
     * 判断键是否存在
     * @param key
     * @return
     */
    Boolean hasKey(K key);

    /**
     * 设置建的过期时间
     * @param key
     * @param time
     * @param timeUnit
     */
    void setExpire(K key, long time, TimeUnit timeUnit);

    /**
     * 获取键的过期时间
     * @param key
     * @return
     */
    Long getExpire(K key);

}
