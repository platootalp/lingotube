package com.moncoder.lingo.video.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moncoder.lingo.video.domain.vo.FavoriteFolderVideoVO;
import com.moncoder.lingo.video.domain.vo.VideoWatchLaterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * @description 自定义用户收藏夹视频表映射
 * @date 2024/4/3 11:39
 */
public interface VmsUserFavoriteFolderVideoDao {

    /**
     * 查询收藏夹下的视频
     *
     * @param page
     * @param videoIds
     * @param titleKeyWord
     * @return
     */
    IPage<FavoriteFolderVideoVO> selectPageByVideoIds(IPage<FavoriteFolderVideoVO> page,
                                                      @Param("videoIds") List<Integer> videoIds,
                                                      @Param("titleKeyWord") String titleKeyWord);
}
