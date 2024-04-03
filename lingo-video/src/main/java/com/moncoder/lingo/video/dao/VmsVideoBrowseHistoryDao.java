package com.moncoder.lingo.video.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moncoder.lingo.video.domain.vo.VideoBrowseHistoryVO;
import org.apache.ibatis.annotations.Param;


/**
 * @author lenovo
 * @version 1.0
 * @description 自定义视频浏览历史表映射
 * @date 2024/4/3 14:16
 */
public interface VmsVideoBrowseHistoryDao {

    /**
     * 查询当前用户全部视频浏览历史
     *
     * @param page
     * @param userId
     * @param titleKeyWord
     * @return
     */
    IPage<VideoBrowseHistoryVO> selectPageByUserId(IPage<VideoBrowseHistoryVO> page,
                                                   @Param("userId") Integer userId,
                                                   @Param("titleKeyWord") String titleKeyWord);
}
