package com.moncoder.lingo.video.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;
import com.moncoder.lingo.video.domain.vo.VideoWatchLaterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Moncoder
 * @version 1.0
 * @description 自定义视频稍后再看映射
 * @date 2024/4/3 16:11
 */
public interface VmsVideoDao {


    /**
     * 根据分类id查询所有视频（分页）
     *
     * @param page
     * @param categoryId
     * @param sort
     * @return
     */
    IPage<VideoViewVO> selectPageByCategoryId(IPage<VideoViewVO> page,
                                              @Param("categoryId") Integer categoryId,
                                              @Param("sort") Integer sort);

    /**
     * 根据等级id查询所有视频（分页）
     *
     * @param page
     * @param levelId
     * @param sort
     * @return
     */
    IPage<VideoViewVO> selectPageByLevelId(IPage<VideoViewVO> page,
                                           @Param("levelId") Integer levelId,
                                           @Param("sort") Integer sort);
}
