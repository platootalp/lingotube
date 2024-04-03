package com.moncoder.lingo.video.dao;

import com.moncoder.lingo.video.domain.vo.VideoBrowseHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param userId
     * @param titleKeyWord
     * @return
     */
    List<VideoBrowseHistoryVO> selectListByUserId(@Param("userId") Integer userId,
                                                  @Param("titleKeyWord") String titleKeyWord);
}
