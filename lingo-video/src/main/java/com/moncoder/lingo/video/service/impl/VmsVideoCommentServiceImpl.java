package com.moncoder.lingo.video.service.impl;

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

}
