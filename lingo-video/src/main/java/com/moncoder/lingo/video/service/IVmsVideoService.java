package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.video.domain.dto.VideoCommentDTO;
import com.moncoder.lingo.video.domain.dto.VideoCreateDTO;

import java.util.List;

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
     * 评论视频
     * @param userId
     * @param videoId
     * @param videoCommentDTO
     * @return
     */
    boolean commentVideo(Integer userId, Integer videoId, VideoCommentDTO videoCommentDTO);
}
