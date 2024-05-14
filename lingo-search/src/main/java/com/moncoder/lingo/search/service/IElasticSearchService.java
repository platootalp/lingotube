package com.moncoder.lingo.search.service;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2024/5/14 10:57
 */
public interface IElasticSearchService {

    /****** 单个操作 ******/
    /**
     * 新增操作
     *
     * @param index  索引库名称
     * @param id     文档id
     * @param source
     * @param target
     */
    void create(String index, Integer id, Object source, Object target);

    /**
     * 单个删除
     *
     * @param index
     * @param id
     */
    void delete(String index, Integer id);

    /**
     * 单个修改
     *
     * @param index
     * @param id
     * @param map
     */
    void update(String index, Integer id, Map<String, Object> map);

    /****** 批量操作 ******/

    /**
     * 批量导入
     * @param index
     * @param map
     * @param targetClass
     */
    void bulkCreate(String index, Map<Integer,Object> map, Class<?> targetClass);

}
