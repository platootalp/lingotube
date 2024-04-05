package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsVideoComment;
import com.moncoder.lingo.video.domain.dto.VideoCommentDTO;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
public interface IVmsVideoCommentService extends IService<VmsVideoComment> {

    /**
     * 评论视频
     * @param videoCommentDTO
     * @return
     */
    boolean commentVideo(VideoCommentDTO videoCommentDTO);

    /**
     * 删除评论（评论及其子评论状态设为已删除:2）
     * @param id
     * @return
     */
    boolean delComment(Integer id);

    /**
     * 点赞评论
     * @param id
     * @param userId
     * @return
     */
    boolean likeComment(Integer id, Integer userId);
}
