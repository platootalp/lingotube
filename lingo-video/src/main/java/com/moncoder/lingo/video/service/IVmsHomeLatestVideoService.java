package com.moncoder.lingo.video.service;

import com.moncoder.lingo.entity.VmsHomeLatestVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;

import java.util.List;

/**
 * <p>
 * 最新视频表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 13:18:19
 */
public interface IVmsHomeLatestVideoService extends IService<VmsHomeLatestVideo> {

    List<VideoViewVO> getLatestVideos();

    boolean disableLatestVideos();
}
