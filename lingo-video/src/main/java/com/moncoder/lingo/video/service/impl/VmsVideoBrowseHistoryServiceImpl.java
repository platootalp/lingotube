package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoBrowseHistory;
import com.moncoder.lingo.mapper.VmsVideoBrowseHistoryMapper;
import com.moncoder.lingo.video.dao.VmsVideoBrowseHistoryDao;
import com.moncoder.lingo.video.domain.dto.VideoBrowseHistoryDTO;
import com.moncoder.lingo.video.domain.vo.VideoBrowseHistoryVO;
import com.moncoder.lingo.video.service.IVmsVideoBrowseHistoryService;
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
public class VmsVideoBrowseHistoryServiceImpl extends ServiceImpl<VmsVideoBrowseHistoryMapper, VmsVideoBrowseHistory> implements IVmsVideoBrowseHistoryService {

    @Autowired
    private VmsVideoBrowseHistoryDao videoBrowseHistoryDao;

    @Override
    public boolean save(VideoBrowseHistoryDTO videoBrowseHistoryDTO) {
        // 1.查询记录是否存在
        Integer userId = videoBrowseHistoryDTO.getUserId();
        Integer videoId = videoBrowseHistoryDTO.getVideoId();
        Integer viewDuration = videoBrowseHistoryDTO.getViewDuration();
        VmsVideoBrowseHistory browseHistory = lambdaQuery().eq(VmsVideoBrowseHistory::getUserId, userId)
                .eq(VmsVideoBrowseHistory::getVideoId, videoId)
                .one();
        // 2.记录存在就修改
        if (browseHistory != null) {
            browseHistory.setViewDuration(viewDuration);
            browseHistory.setCreateTime(LocalDateTime.now());
            return updateById(browseHistory);
        }
        // 3.不存在就新增
        VmsVideoBrowseHistory newBrowseHistory = new VmsVideoBrowseHistory();
        BeanUtils.copyProperties(videoBrowseHistoryDTO, newBrowseHistory);
        newBrowseHistory.setCreateTime(LocalDateTime.now());
        return save(newBrowseHistory);
    }

    @Override
    public boolean deleteBatch(Integer userId, List<Integer> ids) {
        return lambdaUpdate().eq(VmsVideoBrowseHistory::getUserId, userId)
                .in(VmsVideoBrowseHistory::getId, ids)
                .remove();
    }

    @Override
    public List<VideoBrowseHistoryVO> getListByUserId(Integer userId) {
        return null;
    }

    @Override
    public LPage<VideoBrowseHistoryVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize,
                                                       String titleKeyWord) {
        // 1.根据用户id查询出所有VideoBrowseHistoryVO
        Page<VideoBrowseHistoryVO> page = new Page<>(pageNum, pageSize);
        IPage<VideoBrowseHistoryVO> historyVos
                = videoBrowseHistoryDao.selectPageByUserId(page, userId, titleKeyWord);
        // 2.返回分页对象
        return LPage.restPage(historyVos);
    }

}
