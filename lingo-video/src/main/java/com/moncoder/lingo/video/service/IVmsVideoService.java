package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.video.domain.dto.VideoCreateDTO;

import java.util.List;


/**
 * <p>
 * 视频表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
public interface IVmsVideoService extends IService<VmsVideo> {

    /**
     * 上传视频
     *
     * @param vmsVideoDTO
     * @return
     */
    boolean uploadVideo(VideoCreateDTO vmsVideoDTO);

    /**
     * 收藏、取消收藏视频
     *
     * @param userId
     * @param videoId
     * @param folderIds
     * @return
     */
    boolean favoriteVideo(Integer userId, Integer videoId, List<Integer> folderIds);


    /**
     * 点赞、取消点赞视频
     *
     * @param userId
     * @param videoId
     * @return
     */
    boolean likeVideo(Integer userId, Integer videoId);

    /**
     * 保存最新视频到最新视频表中
     * @param videoNum 视频数量
     * @return
     */
    boolean saveLatestVideos(Integer videoNum);

}
