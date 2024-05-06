package com.moncoder.lingo.video.task;

import com.moncoder.lingo.common.constant.VideoConstant;
import com.moncoder.lingo.video.service.IVmsHomeLatestVideoService;
import com.moncoder.lingo.video.service.IVmsHomeRecommendedVideoService;
import com.moncoder.lingo.video.service.IVmsHomeTrendingVideoService;
import com.moncoder.lingo.video.service.IVmsVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO 定时任务
 * @date 2024/5/4 16:17
 */
@Component
@Slf4j
public class HomeVideoScheduledTask {

    @Autowired
    private IVmsHomeLatestVideoService homeLatestVideoService;
    @Autowired
    private IVmsHomeRecommendedVideoService recommendedVideoService;
    @Autowired
    private IVmsHomeTrendingVideoService trendingVideoService;
    @Autowired
    private IVmsVideoService videoService;

    /**
     * 更新首页热门视频任务
     */
    @Scheduled(cron = "0 0 5 ? * MON")
    public void refreshTrendingVideosTask(){
        log.debug("更新首页热门视频: " + LocalDateTime.now());
        // 1.删除旧的首页推荐
        trendingVideoService.disableTrendingVideos();
        // 2.生成新的首页推荐
        videoService.saveTrendingVideos(VideoConstant.VMS_VIDEO_HOME_LATEST_NUM);
    }

    /**
     * 更新首页最新视频任务
     */
    @Scheduled(cron = "0 0 5 ? * MON")
    public void refreshLatestVideosTask(){
        log.debug("更新首页最新视频...");
        // 1.删除旧的首页最新视频
        homeLatestVideoService.disableLatestVideos();
        // 2.生成新的首页最新视频
        videoService.saveLatestVideos(VideoConstant.VMS_VIDEO_HOME_LATEST_NUM);
    }

    /**
     * 更新首页推荐视频任务
     */
    @Scheduled(cron = "0 0 5 ? * MON")
    public void refreshRecommendedVideosTask(){
        log.debug("更新首页推荐视频...");
        // 1.删除旧的首页推荐视频
        recommendedVideoService.disableRecommendedVideos();
        // 2.生成新的首页推荐视频
        videoService.saveRecommendedVideos(VideoConstant.VMS_VIDEO_HOME_RECOMMENDED_NUM);
    }
}

