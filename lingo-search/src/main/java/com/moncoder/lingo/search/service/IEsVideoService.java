package com.moncoder.lingo.search.service;

import com.moncoder.lingo.search.domain.EsVideo;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO 搜索视频接口
 * @date 2024/5/13 14:29
 */
public interface IEsVideoService {

    /**
     * 根据id导入视频到ES中
     * @param id
     * @return
     */
    EsVideo create(Integer id);

    /**
     * 从数据库中导入所有视频到ES中
     * @return
     */
    int importAll();

    /**
     * 删除ES中所有视频
     * @return
     */
    void deleteAll();

    /**
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     *
     * @param ids
     */
    void deleteBatch(List<Integer> ids);
}
