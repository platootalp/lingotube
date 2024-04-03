package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.entity.VmsUserFavoriteFolderVideo;
import com.moncoder.lingo.video.domain.dto.FavoriteFolderVideoCopyDTO;
import com.moncoder.lingo.video.domain.dto.FavoriteFolderVideoMoveDTO;
import com.moncoder.lingo.video.domain.vo.FavoriteFolderVideoVO;


import java.util.List;

/**
 * <p>
 * 用户收藏视频记录表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-01 13:14:15
 */
public interface IVmsUserFavoriteFolderVideoService extends IService<VmsUserFavoriteFolderVideo> {
    /**
     * 获取收藏记录，一个视频可以收藏到多个收藏夹中
     *
     * @param userId
     * @param videoId
     * @param folderIds
     * @return
     */
    List<VmsUserFavoriteFolderVideo> getListByUserIdVideoIdFolderIds(Integer userId, Integer videoId, List<Integer> folderIds);

    /**
     * 根据用户id,和视频id,获取到其所有收藏记录
     *
     * @param userId
     * @param videoId
     * @return
     */

    List<VmsUserFavoriteFolderVideo> getAllByUserIdVideoId(Integer userId, Integer videoId);

    /**
     * 根据用户id和收藏夹id,获取到该收藏夹中所有记录
     *
     * @param userId
     * @param folderId
     * @return
     */
    List<VmsUserFavoriteFolderVideo> getAllByUserIdFolderId(Integer userId, Integer folderId);

    /**
     * 将视频收藏到多个收藏夹中
     *
     * @param userId
     * @param videoId
     * @param folderIds
     * @return
     */
    boolean saveBatchByUserIdVideoIdFolderIds(Integer userId, Integer videoId, List<Integer> folderIds);


    /**
     * 复制当前收藏夹视频到其他收藏夹
     *
     * @param folderVideoCopyDTO
     * @return
     */
    boolean copyVideosToFolders(FavoriteFolderVideoCopyDTO folderVideoCopyDTO);


    /**
     * 移动当前收藏夹视频到其他收藏夹
     *
     * @param folderVideoMoveDTO
     * @return
     */
    boolean moveVideosToFolder(FavoriteFolderVideoMoveDTO folderVideoMoveDTO);

    /**
     * 分页查询收藏夹下的所有视频
     *
     * @param userId
     * @param folderId
     * @param pageNum
     * @param pageSize
     * @param titleKeyWord 视频名字关键字
     * @param orderBy     排序条件，0：按收藏时间，1：按播放量
     * @return
     */
    LPage<FavoriteFolderVideoVO> getPage(Integer userId, Integer folderId, Long pageNum, Long pageSize,
                                         String titleKeyWord, Integer orderBy);
}
