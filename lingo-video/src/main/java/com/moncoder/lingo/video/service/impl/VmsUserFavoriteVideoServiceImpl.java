package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsUserFavoriteVideo;
import com.moncoder.lingo.mapper.VmsUserFavoriteVideoMapper;
import com.moncoder.lingo.video.service.IVmsUserFavoriteVideoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏视频记录表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Service
public class VmsUserFavoriteVideoServiceImpl extends ServiceImpl<VmsUserFavoriteVideoMapper, VmsUserFavoriteVideo> implements IVmsUserFavoriteVideoService {

    @Override
    public VmsUserFavoriteVideo getByUserVideoFolderId(Integer userId, Integer videoId, Integer folderId) {
        return lambdaQuery().eq(VmsUserFavoriteVideo::getUserId, userId)
                .eq(VmsUserFavoriteVideo::getVideoId, videoId)
                .eq(VmsUserFavoriteVideo::getFolderId, folderId)
                .one();
    }
}
