package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsUserFavoriteVideo;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.mapper.VmsVideoMapper;
import com.moncoder.lingo.video.domain.dto.VmsVideoDTO;
import com.moncoder.lingo.video.service.IVmsUserFavoriteVideoService;
import com.moncoder.lingo.video.service.IVmsVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 视频表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Service
public class VmsVideoServiceImpl extends ServiceImpl<VmsVideoMapper, VmsVideo> implements IVmsVideoService {

    @Autowired
    private IVmsUserFavoriteVideoService favoriteVideoService;

    @Override
    public boolean uploadVideo(VmsVideoDTO vmsVideoDTO) {
        // 1.视频保存到服务器，返回视频路径
        // 2.生成视频缩略图，返回缩略图路径
        // 3.保存视频实体到数据库中
        VmsVideo video = new VmsVideo();
        BeanUtils.copyProperties(vmsVideoDTO,video);
        return save(video);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean favoriteVideo(Integer userId, Integer videoId, Integer folderId) {
        // 1.获取当前视频收藏数
        VmsVideo video = lambdaQuery().eq(VmsVideo::getId, videoId).one();
        Integer favorites = video.getFavorites();
        // 2.查看收藏视频表是否有记录
        VmsUserFavoriteVideo favoriteVideo = favoriteVideoService.getByUserVideoFolderId(userId, videoId, folderId);
        // 3.如果没有则为收藏
        if (favoriteVideo == null) {
            // 收藏视频表记录
            favoriteVideo = new VmsUserFavoriteVideo();
            favoriteVideo.setUserId(userId);
            favoriteVideo.setVideoId(videoId);
            favoriteVideo.setFolderId(folderId);
            favoriteVideoService.save(favoriteVideo);
            // 当前视频收藏数+1
            return lambdaUpdate().eq(VmsVideo::getId, videoId).set(VmsVideo::getFavorites, favorites + 1)
                    .update();
        }
        // 4.如果有则为取消收藏
        favoriteVideoService.removeById(favoriteVideo.getId());
        return lambdaUpdate().eq(VmsVideo::getId, videoId).set(VmsVideo::getFavorites, favorites - 1)
                .update();
    }
}
