package com.moncoder.lingo.common.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author Moncoder
 * @version 1.0
 * @description Redis操作接口
 * @date 2024/3/21 13:12
 */
public interface IRedisService {


    /**********string操作**********/

    /**
     * 保存键值对
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 保存键值对（带过期时间）
     *
     * @param key
     * @param value
     * @param time  （单位：秒）
     */
    void set(String key, Object value, long time);

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除键
     *
     * @param key
     */
    Boolean delete(String key);

    /**
     * 删除键
     *
     * @param key
     */
    Long deleteBatch(Collection<String> keys);

    /**
     * 判断键是否存在
     *
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 设置建的过期时间
     *
     * @param key
     * @param time
     */
    void expire(String key, long time);

    /**
     * 获取键的过期时间
     *
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 键值增加
     *
     * @param key
     * @param delta 增加的数值
     */
    Long incr(String key, long delta);

    /********** hash操作 **********/
    /**
     * 保存hash数据
     *
     * @param key
     * @param hashKey
     * @param hashValue
     */
    void hSet(String key, String hashKey, Object hashValue);

    /**
     * 批量保存hash数据
     *
     * @param key
     * @param map
     */
    void hSetAll(String key, Map<String, Object> map);

    /**
     * 获取Hash结构中的属性
     */
    Object hGet(String key, String hashKey);

    /**
     * 直接获取整个Hash结构
     */
    Map<Object, Object> hGetAll(String key);
}
