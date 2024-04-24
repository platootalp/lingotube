package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoWatchLater;
import com.moncoder.lingo.video.domain.dto.VideoWatchLaterDTO;
import com.moncoder.lingo.video.domain.vo.VideoWatchLaterVO;

import java.util.List;

/**
 * <p>
 * 稍后再看视频表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 15:46:27
 */
public interface IVmsVideoWatchLaterService extends IService<VmsVideoWatchLater> {

    /**
     * 查看记录是否存在
     * @param userId
     * @param videoId
     * @return
     */
    boolean exist(Integer userId, Integer videoId);

    /**
     * 保存稍后再看记录
     *
     * @param videoWatchLaterDTO
     * @return
     */
    boolean save(VideoWatchLaterDTO videoWatchLaterDTO);

    /**
     * 删除稍后再看记录
     * @param userId
     * @param videoId
     * @return
     */
    boolean delete(Integer userId, Integer videoId);

    /**
     * 批量删除稍后再看记录
     *
     * @param userId
     * @param ids
     * @return
     */
    boolean deleteBatch(Integer userId, List<Integer> ids);

    /**
     * 删除全部稍后再看记录
     *
     * @param userId
     * @return
     */
    boolean deleteAll(Integer userId);

    /**
     * 删除已观看记录
     *
     * @param userId
     * @return
     */
    boolean deleteWatch(Integer userId);

    /**
     * 分页查询全部稍后再看记录
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param titleKeyWord
     * @return
     */
    LPage<VideoWatchLaterVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize, String titleKeyWord);

    List<VideoWatchLaterVO> getListByUserId(Integer userId, Integer sort);
}
