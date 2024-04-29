package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.video.domain.vo.VideoPlayVO;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;
import io.swagger.models.auth.In;

import java.util.List;


/**
 * <p>
 * 视频表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
public interface IVmsVideoService extends IService<VmsVideo> {

    /**
     * 根据id获取视频播放信息
     * @param id
     * @return
     */
    VideoPlayVO getVideo(Integer id);

    /**
     * 获取视频点赞数
     * @param id
     * @return
     */
    Integer getVideoLikes(Integer id);

    /**
     * 收藏、取消收藏视频
     *
     * @param userId
     * @param videoId
     * @param folderIds
     * @return
     */
    boolean favoriteVideo(Integer userId, Integer videoId, List<Integer> folderIds);

    /**
     * 保存最新视频到最新视频表中
     *
     * @param videoNum 视频数量
     * @return
     */
    boolean saveLatestVideos(Integer videoNum);

    /**
     * 保存热门视频到热门视频表中
     *
     * @param videoNum 视频数量
     * @return
     */
    boolean saveTrendingVideos(Integer videoNum);

    /**
     * 保存推荐视频到推荐视频表中
     *
     * @param videoNum 视频数量
     * @return
     */
    boolean saveRecommendedVideos(Integer videoNum);


    /**
     * 获取相关视频
     * @param id
     * @param levelName
     * @return
     */
    List<VideoViewVO> getRelatedVideos(Integer id, String levelName,int num);

    /**
     * 获取分类视频（分页）
     * @param categoryId
     * @param curPage
     * @param pageSize
     * @param sort
     * @return
     */
    LPage<VideoViewVO> getPageByCategoryId(Integer categoryId, Long curPage, Long pageSize, Integer sort);

    /**
     * 获取分等级的视频（分页）
     * @param levelId
     * @param curPage
     * @param pageSize
     * @param sort
     * @return
     */
    LPage<VideoViewVO> getPageByLevelId(Integer levelId, Long curPage, Long pageSize, Integer sort);

    /**
     * 增加视频播放量
     * @param id
     * @param num
     * @return
     */
    boolean addVideoViews(Integer id, Integer num);
}
