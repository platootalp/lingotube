package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoBrowseHistory;
import com.moncoder.lingo.video.domain.vo.VideoBrowseHistoryVO;

import java.util.List;

/**
 * <p>
 * 用户浏览视频历史记录表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 13:40:30
 */
public interface IVmsVideoBrowseHistoryService extends IService<VmsVideoBrowseHistory> {

    /**
     * 批量删除视频浏览历史
     *
     * @param userId
     * @param ids
     * @return
     */
    boolean deleteBatch(Integer userId, List<Integer> ids);

    /**
     * 查询当前用户全部视频浏览历史
     *
     * @param userId
     * @return
     */
    List<VideoBrowseHistoryVO> getListByUserId(Integer userId);

    /**
     * 分页查询当前用户全部视频浏览历史
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param titleKeyWord
     * @return
     */
    LPage<VideoBrowseHistoryVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize, String titleKeyWord);
}
