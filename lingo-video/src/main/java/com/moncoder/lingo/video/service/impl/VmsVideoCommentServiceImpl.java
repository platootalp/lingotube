package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.entity.VmsVideoComment;
import com.moncoder.lingo.entity.VmsVideoCommentLike;
import com.moncoder.lingo.mapper.VmsVideoCommentMapper;
import com.moncoder.lingo.video.domain.dto.VideoCommentDTO;
import com.moncoder.lingo.video.service.IVmsVideoCommentService;
import com.moncoder.lingo.video.service.IVmsVideoService;
import com.moncoder.lingo.video.service.service.IVmsVideoCommentLikeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Service
public class VmsVideoCommentServiceImpl extends ServiceImpl<VmsVideoCommentMapper, VmsVideoComment> implements IVmsVideoCommentService {

    @Autowired
    private IVmsVideoService videoService;
    @Autowired
    private IVmsVideoCommentLikeService videoCommentLikeService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean commentVideo(VideoCommentDTO videoCommentDTO) {
        // 1. 更新视频评论数
        Integer videoId = videoCommentDTO.getVideoId();
        VmsVideo video = videoService.lambdaQuery().eq(VmsVideo::getId, videoId).one();
        videoService.lambdaUpdate().eq(VmsVideo::getId, videoId)
                .set(VmsVideo::getComments, video.getComments() + 1)
                .update();

        // 2. 更新父评论的回复数（如果有）
        Integer parentId = videoCommentDTO.getParentId();
        if (parentId != null && parentId != 0) {
            lambdaUpdate().eq(VmsVideoComment::getId, parentId)
                    .setSql("replies = replies + 1")
                    .update();
        }

        // 3. 增加评论记录
        VmsVideoComment videoComment = new VmsVideoComment();
        BeanUtils.copyProperties(videoCommentDTO, videoComment);
        videoComment.setStatus((byte) 1);
        videoComment.setLikes(0);
        videoComment.setReplies(0);
        return save(videoComment);
    }


    @Override
    public boolean delComment(Integer id) {
        // 1.初始化要删除的评论 ID 列表
        List<Integer> idsToDelete = new ArrayList<>();
        idsToDelete.add(id);
        // 2.树形遍历，找到所有子评论的 ID
        traverseTree(id, idsToDelete);

        // 3.执行批量删除操作（状态设为已删除）
        return lambdaUpdate().in(VmsVideoComment::getId, idsToDelete)
                .set(VmsVideoComment::getStatus, 2)
                .update();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean likeComment(Integer id, Integer userId) {
        // 1.根据评论id与用户id查询评论点赞表，评论点赞状态
        VmsVideoCommentLike commentLike = videoCommentLikeService.getByUserIdAndCommentId(userId, id);
        // 判断点赞状态
        boolean isLiked = commentLike != null && commentLike.getIsActive().equals((byte) 1);
        // 2.更新点赞状态
        boolean success = false;
        if (isLiked) {
            // 取消点赞
            commentLike.setIsActive((byte) 0);
            commentLike.setUpdateTime(LocalDateTime.now());
            success = videoCommentLikeService.updateById(commentLike);
        } else {
            // 点赞
            if (commentLike == null) {
                // 新增点赞记录
                VmsVideoCommentLike newCommentLike = new VmsVideoCommentLike();
                newCommentLike.setUserId(userId);
                newCommentLike.setCommentId(id);
                newCommentLike.setIsActive((byte) 1);
                newCommentLike.setCreateTime(LocalDateTime.now());
                videoCommentLikeService.save(newCommentLike);
            } else {
                // 更新点赞状态为已点赞
                commentLike.setIsActive((byte) 1);
                commentLike.setUpdateTime(LocalDateTime.now());
                videoCommentLikeService.updateById(commentLike);
            }
        }
        // 3.更新点赞数
        if (success || !isLiked) {
            // 当前评论点赞数加一或减一
            return lambdaUpdate().eq(VmsVideoComment::getId, id)
                    .setSql(isLiked ? "likes = likes - 1" : "likes = likes + 1")
                    .update();
        }
        return false;
    }

    /**
     * 递归遍历评论树，找到所有子评论的 ID
     *
     * @param parentId
     * @param idsToDelete
     */
    private void traverseTree(Integer parentId, List<Integer> idsToDelete) {
        // 1.获取当前评论的全部子评论
        List<VmsVideoComment> childComments = lambdaQuery().eq(VmsVideoComment::getParentId, parentId).list();
        // 2.遍历子评论
        for (VmsVideoComment childComment : childComments) {
            Integer childCommentId = childComment.getId();
            // 添加子评论的 ID 到要删除的列表中
            idsToDelete.add(childCommentId);
            // 递归遍历子评论的子评论
            traverseTree(childCommentId, idsToDelete);
        }
    }


}
