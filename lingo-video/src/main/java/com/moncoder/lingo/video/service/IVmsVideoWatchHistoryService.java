package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoWatchHistory;
import com.moncoder.lingo.video.domain.dto.VideoWatchHistoryDTO;
import com.moncoder.lingo.video.domain.vo.VideoWatchHistoryVO;

import java.util.List;

/**
 * <p>
 * 用户浏览视频历史记录表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 13:40:30
 */
public interface IVmsVideoWatchHistoryService extends IService<VmsVideoWatchHistory> {

    /**
     * 保存浏览历史
     *
     * @param videoWatchHistoryDTO
     * @return
     */
    boolean save(VideoWatchHistoryDTO videoWatchHistoryDTO);

    /**
     * 查询当前用户全部视频浏览历史
     *
     * @param userId
     * @return
     */
    List<VideoWatchHistoryVO> getListByUserId(Integer userId,String titleKeyWord);

    /**
     * 清空视频观看历史
     * @param userId
     * @return
     */
    boolean clear(Integer userId);

    /**
     * 批量删除视频浏览历史
     *
     * @param userId
     * @param videoIds
     * @return
     */
    boolean deleteBatch(Integer userId, List<Integer> videoIds);

    /**
     * 分页查询当前用户全部视频浏览历史
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param titleKeyWord
     * @return
     */
    LPage<VideoWatchHistoryVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize, String titleKeyWord);
}
