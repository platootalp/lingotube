package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsVideoCommentLike;

/**
 * <p>
 * 评论点赞表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-05 11:20:05
 */
public interface IVmsVideoCommentLikeService extends IService<VmsVideoCommentLike> {

    /**
     * 根据用户id和评论id获取评论点赞记录
     * @param userId 用户id
     * @param commentId 评论id
     * @return
     */
    VmsVideoCommentLike getByUserIdAndCommentId(Integer userId, Integer commentId);
}
