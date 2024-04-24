package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoWatchHistory;
import com.moncoder.lingo.mapper.VmsVideoWatchHistoryMapper;
import com.moncoder.lingo.video.dao.VmsVideoWatchHistoryDao;
import com.moncoder.lingo.video.domain.dto.VideoWatchHistoryDTO;
import com.moncoder.lingo.video.domain.vo.VideoWatchHistoryVO;
import com.moncoder.lingo.video.service.IVmsVideoWatchHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户浏览视频历史记录表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 13:40:30
 */
@Service
public class VmsVideoWatchHistoryServiceImpl extends ServiceImpl<VmsVideoWatchHistoryMapper, VmsVideoWatchHistory> implements IVmsVideoWatchHistoryService {

    @Autowired
    private VmsVideoWatchHistoryDao videoWatchHistoryDao;

    @Override
    public boolean save(VideoWatchHistoryDTO videoWatchHistoryDTO) {
        // 1.查询记录是否存在
        Integer userId = videoWatchHistoryDTO.getUserId();
        Integer videoId = videoWatchHistoryDTO.getVideoId();
        Integer viewDuration = videoWatchHistoryDTO.getViewDuration();
        VmsVideoWatchHistory watchHistory = lambdaQuery().eq(VmsVideoWatchHistory::getUserId, userId)
                .eq(VmsVideoWatchHistory::getVideoId, videoId)
                .one();
        // 2.记录存在就修改
        if (watchHistory != null) {
            watchHistory.setViewDuration(viewDuration);
            watchHistory.setWatchTime(LocalDateTime.now());
            return updateById(watchHistory);
        }
        // 3.不存在就新增
        VmsVideoWatchHistory newBrowseHistory = new VmsVideoWatchHistory();
        BeanUtils.copyProperties(videoWatchHistoryDTO, newBrowseHistory);
        newBrowseHistory.setWatchTime(LocalDateTime.now());
        return save(newBrowseHistory);
    }

    @Override
    public List<VideoWatchHistoryVO> getListByUserId(Integer userId, String titleKeyWord) {
        return videoWatchHistoryDao.selectListByUserId(userId, titleKeyWord);
    }

    @Override
    public boolean clear(Integer userId) {
        return lambdaUpdate().eq(VmsVideoWatchHistory::getUserId, userId)
                .remove();
    }

    @Override
    public boolean deleteBatch(Integer userId, List<Integer> videoIds) {
        return lambdaUpdate().eq(VmsVideoWatchHistory::getUserId, userId)
                .in(VmsVideoWatchHistory::getVideoId, videoIds)
                .remove();
    }

    @Override
    public LPage<VideoWatchHistoryVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize,
                                                      String titleKeyWord) {
        // 1.根据用户id查询出所有VideoBrowseHistoryVO
        Page<VideoWatchHistoryVO> page = new Page<>(pageNum, pageSize);
        IPage<VideoWatchHistoryVO> historyVos
                = videoWatchHistoryDao.selectPageByUserId(page, userId, titleKeyWord);
        // 2.返回分页对象
        return LPage.restPage(historyVos);
    }

}
