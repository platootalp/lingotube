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
     * 查看点赞记录是否存在
     *
     * @param userId
     * @param videoId
     * @return
     */
    boolean exist(Integer userId, Integer videoId);

    /**
     * 点赞、取消点赞视频
     *
     * @param userId
     * @param videoId
     * @return
     */
    boolean likeVideo(Integer userId, Integer videoId);


    /**
     * 获取用户全部点赞记录
     *
     * @param userId
     * @param titleKeyWord
     * @return
     */
    List<VideoLikeVO> getListByUserId(Integer userId, String titleKeyWord);

    /**
     * @param userId
     * @param videoId
     * @return
     */
    VmsVideoLike getOne(Integer userId, Integer videoId);

    /**
     * 删除点赞记录
     * @param userId
     * @param videoId
     * @return
     */
    boolean deleteOne(Integer userId, Integer videoId);

    /**
     * 清空点赞记录
     * @param userId
     * @return
     */
    boolean clear(Integer userId);

    /**
     * @param userId
     * @param ids
     * @return
     */
    boolean deleteBatch(Integer userId, List<Integer> ids);

    /**
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param titleKeyWord
     * @return
     */
    LPage<VideoLikeVO> getPageByUserId(Integer userId, Long pageNum, Long pageSize, String titleKeyWord);


}
