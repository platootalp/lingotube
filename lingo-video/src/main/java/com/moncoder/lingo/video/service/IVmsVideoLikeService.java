package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideoLike;
import com.moncoder.lingo.video.domain.vo.VideoLikeVO;

import java.util.List;

/**
 * <p>
 * 视频点赞表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 19:07:44
 */
public interface IVmsVideoLikeService extends IService<VmsVideoLike> {


    /**
     *
     * @param userId
     * @param videoId
     * @return
     */
    VmsVideoLike getByUserIdAndVideoId(Integer userId, Integer videoId);

    /**
     *
     * @param userId
     * @param ids
     * @return
     */
    boolean deleteBatch(Integer userId, List<Integer> ids);

    /**
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param titleKeyWord
     * @return
     */
    LPage<VideoLikeVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize, String titleKeyWord);
}
