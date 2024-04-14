package com.moncoder.lingo.video.service.impl;

import com.moncoder.lingo.entity.VmsHomeLatestVideo;
import com.moncoder.lingo.entity.VmsHomeRecommendedVideo;
import com.moncoder.lingo.mapper.VmsHomeLatestVideoMapper;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;
import com.moncoder.lingo.video.service.IVmsHomeLatestVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 最新视频表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 13:18:19
 */
@Service
public class VmsHomeLatestVideoServiceImpl extends ServiceImpl<VmsHomeLatestVideoMapper, VmsHomeLatestVideo> implements IVmsHomeLatestVideoService {

    @Override
    public List<VideoViewVO> getLatestVideos() {
        List<VmsHomeLatestVideo> videos =
                lambdaQuery().eq(VmsHomeLatestVideo::getStatus, (byte) 1).list();
        return videos.stream().map(video -> {
            VideoViewVO videoViewVO = new VideoViewVO();
            BeanUtils.copyProperties(video, videoViewVO);
            return videoViewVO;
        }).collect(Collectors.toList());
    }
}
