package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoBrowseHistory;
import com.moncoder.lingo.mapper.VmsVideoBrowseHistoryMapper;
import com.moncoder.lingo.video.dao.VmsVideoBrowseHistoryDao;
import com.moncoder.lingo.video.domain.vo.VideoBrowseHistoryVO;
import com.moncoder.lingo.video.service.IVmsVideoBrowseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<VideoBrowseHistoryVO> historyVOS = videoBrowseHistoryDao.selectListByUserId(userId,titleKeyWord);
        // 1.设置分页
        Page<VideoBrowseHistoryVO> voPage = new Page<>(pageNum, pageSize);
        voPage.setRecords(historyVOS);
        return LPage.restPage(voPage);
    }

}
