package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsUserFavoriteVideo;

/**
 * <p>
 * 用户收藏视频记录表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
public interface IVmsUserFavoriteVideoService extends IService<VmsUserFavoriteVideo> {

    /**
     * 根据参数获取记录
     * @param userId
     * @param videoId
     * @param folderId
     * @return
     */
    VmsUserFavoriteVideo getByUserVideoFolderId(Integer userId, Integer videoId, Integer folderId);
}
