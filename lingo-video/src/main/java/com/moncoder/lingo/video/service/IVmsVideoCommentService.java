package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsVideoComment;
import com.moncoder.lingo.video.domain.dto.VideoCommentDTO;
import com.moncoder.lingo.video.domain.vo.VideoCommentNodeVO;

import java.util.List;

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

    /**
     * 树形结构获取当前视频全部评论
     * @param videoId
     * @return
     */
    List<VideoCommentNodeVO> treeList(Integer videoId);
}
