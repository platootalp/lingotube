package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.common.constant.VideoConstant;
import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.entity.VmsVideoLike;
import com.moncoder.lingo.mapper.VmsVideoLikeMapper;
import com.moncoder.lingo.video.dao.VmsVideoLikeDao;
import com.moncoder.lingo.video.domain.vo.VideoLikeVO;
import com.moncoder.lingo.video.domain.vo.VideoPlayVO;
import com.moncoder.lingo.video.service.IVmsVideoLikeService;
import com.moncoder.lingo.video.service.IVmsVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IVmsVideoService videoService;

    @Override
    public boolean exist(Integer userId, Integer videoId) {
        VmsVideoLike videoLike = getOne(userId, videoId);
        return videoLike != null && videoLike.getIsLiked().equals((byte) 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean likeVideo(Integer userId, Integer videoId) {
        // 1. 从缓存中获取视频信息，如果缓存中没有，则从数据库获取
        VideoPlayVO videoPlayVO = (VideoPlayVO) redisService.get(VideoConstant.VMS_VIDEO_PLAYER_KEY + videoId);
        Integer likes;
        if (videoPlayVO == null) {
            likes = videoService.getById(videoId).getLikes();
        } else {
            likes = videoPlayVO.getLikes();
        }

        // 2. 查看点赞记录表是否有记录
        VmsVideoLike videoLike = getOne(userId, videoId);
        if (videoLike == null) {
            // 2.1 点赞
            videoLike = new VmsVideoLike();
            videoLike.setUserId(userId);
            videoLike.setVideoId(videoId);
            videoLike.setIsLiked((byte) 1);
            videoLike.setCreateTime(LocalDateTime.now());
            save(videoLike);
            likes++;
        } else if (videoLike.getIsLiked() == (byte) 0) {
            // 2.2 更新为已点赞状态
            videoLike.setIsLiked((byte) 1);
            updateById(videoLike);
            likes++;
        } else {
            // 2.3 取消点赞
            videoLike.setIsLiked((byte) 0);
            updateById(videoLike);
            likes--;
        }

        // 3. 修改点赞数并保存到数据库
        videoService.lambdaUpdate().eq(VmsVideo::getId, videoId).set(VmsVideo::getLikes, likes).update();
        // 4. 删除缓存
        redisService.delete(VideoConstant.VMS_VIDEO_PLAYER_KEY + videoId);
        return true;
    }

    @Override
    public List<VideoLikeVO> getListByUserId(Integer userId, String titleKeyWord) {
        return videoLikeDao.selectListByUserId(userId, titleKeyWord);
    }

    @Override
    public VmsVideoLike getOne(Integer userId, Integer videoId) {
        return lambdaQuery().eq(VmsVideoLike::getUserId, userId)
                .eq(VmsVideoLike::getVideoId, videoId)
                .one();
    }

    @Override
    public boolean deleteOne(Integer userId, Integer videoId) {
        lambdaUpdate().eq(VmsVideoLike::getUserId, userId)
                .eq(VmsVideoLike::getVideoId, videoId)
                .remove();
        videoService.lambdaUpdate().eq(VmsVideo::getId, videoId)
                .setSql("likes = likes - 1")
                .update();
        redisService.delete(VideoConstant.VMS_VIDEO_PLAYER_KEY + videoId);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int clear(Integer userId) {
        // 1.获取点赞记录中的videoId
        List<Integer> videoIds = lambdaQuery().eq(VmsVideoLike::getUserId, userId)
                .eq(VmsVideoLike::getIsLiked, (byte) 1).list()
                .stream().map(VmsVideoLike::getVideoId).collect(Collectors.toList());
        // 2.判断点赞记录是否为0
        if(videoIds.size() == 0){
            return 0;
        }
        // 3.更新数据库
        lambdaUpdate().eq(VmsVideoLike::getUserId, userId)
                .eq(VmsVideoLike::getIsLiked, (byte) 1)
                .remove();
        videoService.lambdaUpdate().in(VmsVideo::getId, videoIds)
                .setSql("likes = likes - 1")
                .update();
        // 4.删除缓存
        List<String> keys = videoIds.stream()
                .map(videoId -> VideoConstant.VMS_VIDEO_PLAYER_KEY + videoId)
                .collect(Collectors.toList());
        redisService.deleteBatch(keys);
        return videoIds.size();
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
