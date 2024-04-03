package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoLike;
import com.moncoder.lingo.mapper.VmsVideoLikeMapper;
import com.moncoder.lingo.video.dao.VmsVideoLikeDao;
import com.moncoder.lingo.video.domain.vo.VideoLikeVO;
import com.moncoder.lingo.video.service.IVmsVideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 视频点赞表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 19:07:44
 */
@Service
public class VmsVideoLikeServiceImpl extends ServiceImpl<VmsVideoLikeMapper, VmsVideoLike> implements IVmsVideoLikeService {

    @Autowired
    private VmsVideoLikeDao videoLikeDao;

    @Override
    public VmsVideoLike getByUserIdAndVideoId(Integer userId, Integer videoId) {
        return lambdaQuery().eq(VmsVideoLike::getUserId, userId)
                .eq(VmsVideoLike::getVideoId, videoId)
                .one();
    }

    @Override
    public boolean deleteBatch(Integer userId, List<Integer> ids) {
        return lambdaUpdate().eq(VmsVideoLike::getUserId, userId)
                .in(VmsVideoLike::getId, ids)
                .remove();
    }

    @Override
    public LPage<VideoLikeVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize, String titleKeyWord) {
        // 1.根据用户id查询出所有记录
        Page<VideoLikeVO> page = new Page<>(pageNum, pageSize);
        IPage<VideoLikeVO> videoLikeVos = videoLikeDao.selectPageByUserId(page, userId, titleKeyWord);
        // 2.返回分页对象

        return LPage.restPage(videoLikeVos);
    }
}
