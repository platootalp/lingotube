package com.moncoder.lingo.video.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moncoder.lingo.video.domain.vo.VideoWatchLaterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Moncoder
 * @version 1.0
 * @description 自定义视频稍后再看映射
 * @date 2024/4/3 16:11
 */
public interface VmsVideoWatchLaterDao {

    /**
     * 根据用户id查询所有稍后再看记录
     *
     * @param userId
     * @param sort
     * @return
     */
    List<VideoWatchLaterVO> selectListByUserId(Integer userId, Integer sort);

    /**
     * 根据用户id查询所有稍后再看记录（分页）
     *
     * @param page         分页条件
     * @param userId
     * @param titleKeyWord
     * @return
     */
    IPage<VideoWatchLaterVO> selectPageByUserId(IPage<VideoWatchLaterVO> page,
                                                @Param("userId") Integer userId,
                                                @Param("titleKeyWord") String titleKeyWord);

}
