package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.entity.VmsVideoComment;
import com.moncoder.lingo.mapper.VmsVideoCommentMapper;
import com.moncoder.lingo.video.domain.dto.VideoCommentDTO;
import com.moncoder.lingo.video.service.IVmsVideoCommentService;
import com.moncoder.lingo.video.service.IVmsVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean commentVideo(Integer userId, Integer videoId, VideoCommentDTO videoCommentDTO) {
        // 1.视频表评论数+1
        VmsVideo video = videoService.lambdaQuery().eq(VmsVideo::getId, videoId).one();
        videoService.lambdaUpdate().eq(VmsVideo::getId, video)
                .set(VmsVideo::getComments, video.getComments() + 1)
                .update();
        // 2.视频评论表增加记录
        VmsVideoComment videoComment = new VmsVideoComment();
        BeanUtils.copyProperties(videoCommentDTO, videoComment);
        videoComment.setParentId(0);
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
