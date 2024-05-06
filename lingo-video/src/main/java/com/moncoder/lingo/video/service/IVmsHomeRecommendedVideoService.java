package com.moncoder.lingo.video.service;

import com.moncoder.lingo.entity.VmsHomeRecommendedVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;

import java.util.List;

/**
 * <p>
 * 推荐视频表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 13:18:20
 */
public interface IVmsHomeRecommendedVideoService extends IService<VmsHomeRecommendedVideo> {

    /**
     * 获取首页推荐视频
     * @return
     */
    List<VideoViewVO> getRecommendedVideos();

    /**
     * 取消首页推荐
     * @return
     */
    boolean disableRecommendedVideos();
}
