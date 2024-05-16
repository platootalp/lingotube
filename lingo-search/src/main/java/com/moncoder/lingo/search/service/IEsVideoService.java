package com.moncoder.lingo.search.service;

import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.search.domain.EsVideo;
import org.springframework.web.bind.annotation.RequestParam;

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
     *
     * @param id
     * @return
     */
    EsVideo create(Integer id);

    /**
     * 从数据库中导入所有视频到ES中
     *
     * @return
     */
    int importAll();

    /**
     * 删除ES中所有视频
     *
     * @return
     */
    void deleteAll();

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 搜索
     *
     * @param key
     * @param levels
     * @param categories
     * @param sortBy
     * @param pageNum
     * @param pageSize
     * @return
     */
    LPage<EsVideo> search(String key, List<String> levels, List<String> categories,
                          Integer minDuration, Integer maxDuration,
                          Integer sortBy, Integer pageNum, Integer pageSize);
}
