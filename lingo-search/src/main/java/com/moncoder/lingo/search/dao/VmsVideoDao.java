package com.moncoder.lingo.search.dao;

import com.moncoder.lingo.search.domain.EsVideo;

import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description 搜索视频数据库操作
 * @date 2024/5/13 14:59
 */
public interface VmsVideoDao {

    /**
     * 获取全部的视频
     * @return
     */
    List<EsVideo> selectAllVideos();
}
