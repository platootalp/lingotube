package com.moncoder.lingo.video.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsVideoCommentLike;
import com.moncoder.lingo.mapper.VmsVideoCommentLikeMapper;
import com.moncoder.lingo.video.service.service.IVmsVideoCommentLikeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论点赞表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-05 11:20:05
 */
@Service
public class VmsVideoCommentLikeServiceImpl extends ServiceImpl<VmsVideoCommentLikeMapper, VmsVideoCommentLike> implements IVmsVideoCommentLikeService {

    @Override
    public VmsVideoCommentLike getByUserIdAndCommentId(Integer userId, Integer commentId) {
        return lambdaQuery().eq(VmsVideoCommentLike::getUserId, userId)
                .eq(VmsVideoCommentLike::getCommentId, commentId)
                .one();
    }
}
