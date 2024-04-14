package com.moncoder.lingo.video.service.impl;

import com.moncoder.lingo.entity.VmsHomeRecommendedVideo;
import com.moncoder.lingo.entity.VmsHomeTrendingVideo;
import com.moncoder.lingo.mapper.VmsHomeTrendingVideoMapper;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;
import com.moncoder.lingo.video.service.IVmsHomeTrendingVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 最热视频表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 13:18:20
 */
@Service
public class VmsHomeTrendingVideoServiceImpl extends ServiceImpl<VmsHomeTrendingVideoMapper, VmsHomeTrendingVideo> implements IVmsHomeTrendingVideoService {

    @Override
    public List<VideoViewVO> getTrendingVideos() {
        List<VmsHomeTrendingVideo> videos =
                lambdaQuery().eq(VmsHomeTrendingVideo::getStatus, (byte) 1).list();
        return videos.stream().map(video -> {
            VideoViewVO videoViewVO = new VideoViewVO();
            BeanUtils.copyProperties(video, videoViewVO);
            return videoViewVO;
        }).collect(Collectors.toList());
    }
}
