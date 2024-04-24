package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoWatchLater;
import com.moncoder.lingo.mapper.VmsVideoWatchLaterMapper;
import com.moncoder.lingo.video.dao.VmsVideoWatchLaterDao;
import com.moncoder.lingo.video.domain.dto.VideoWatchLaterDTO;
import com.moncoder.lingo.video.domain.vo.VideoWatchLaterVO;
import com.moncoder.lingo.video.service.IVmsVideoWatchLaterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 稍后再看视频表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 15:46:27
 */
@Service
public class VmsVideoWatchLaterServiceImpl extends ServiceImpl<VmsVideoWatchLaterMapper, VmsVideoWatchLater> implements IVmsVideoWatchLaterService {

    @Autowired
    private VmsVideoWatchLaterDao videoWatchLaterDao;

    @Override
    public boolean exist(Integer userId, Integer videoId) {
        return lambdaQuery().eq(VmsVideoWatchLater::getUserId, userId)
                .eq(VmsVideoWatchLater::getVideoId, videoId)
                .exists();
    }

    @Override
    public boolean save(VideoWatchLaterDTO videoWatchLaterDTO) {
        // 1.查询记录是否存在
        Integer userId = videoWatchLaterDTO.getUserId();
        Integer videoId = videoWatchLaterDTO.getVideoId();
        Integer viewDuration = videoWatchLaterDTO.getViewDuration();
        VmsVideoWatchLater videoWatchLater = lambdaQuery().eq(VmsVideoWatchLater::getUserId, userId)
                .eq(VmsVideoWatchLater::getVideoId, videoId)
                .one();
        // 2.记录存在且观看时间有变化就修改
        if (videoWatchLater != null && videoWatchLater.getViewDuration().equals(viewDuration)) {
            return true;
        } else if (videoWatchLater != null && !videoWatchLater.getViewDuration().equals(viewDuration)) {
            videoWatchLater.setViewDuration(viewDuration);
            videoWatchLater.setCreateTime(LocalDateTime.now());
            if (viewDuration > 0) {
                videoWatchLater.setIsWatched((byte) 1);
            } else {
                videoWatchLater.setIsWatched((byte) 0);
            }
            return updateById(videoWatchLater);
        }
        // 3.不存在就新增
        VmsVideoWatchLater newVideoWatchLater = new VmsVideoWatchLater();
        BeanUtils.copyProperties(videoWatchLaterDTO, newVideoWatchLater);
        newVideoWatchLater.setCreateTime(LocalDateTime.now());
        if (viewDuration > 0) {
            newVideoWatchLater.setIsWatched((byte) 1);
        } else {
            newVideoWatchLater.setIsWatched((byte) 0);
        }
        return save(newVideoWatchLater);
    }

    @Override
    public boolean delete(Integer userId, Integer videoId) {
        return lambdaUpdate().eq(VmsVideoWatchLater::getUserId, userId)
                .eq(VmsVideoWatchLater::getVideoId, videoId)
                .remove();
    }

    @Override
    public boolean deleteBatch(Integer userId, List<Integer> ids) {
        return lambdaUpdate().eq(VmsVideoWatchLater::getUserId, userId)
                .in(VmsVideoWatchLater::getId, ids)
                .remove();
    }

    @Override
    public boolean deleteAll(Integer userId) {
        return lambdaUpdate().eq(VmsVideoWatchLater::getUserId, userId)
                .remove();
    }

    @Override
    public boolean deleteWatch(Integer userId) {
        return lambdaUpdate().eq(VmsVideoWatchLater::getUserId, userId)
                .eq(VmsVideoWatchLater::getIsWatched, (byte) 1)
                .remove();
    }

    @Override
    public LPage<VideoWatchLaterVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize,
                                                    String titleKeyWord) {
        // 1.根据用户id查询出所有记录
        Page<VideoWatchLaterVO> page = new Page<>(pageNum, pageSize);
        IPage<VideoWatchLaterVO> watchLaterVos = videoWatchLaterDao.selectPageByUserId(
                page, userId, titleKeyWord);
        // 2.返回分页对象
        return LPage.restPage(watchLaterVos);

    }
}
