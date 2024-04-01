package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsUserFavoriteFolderVideo;

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
     * 获取收藏的视频，一个视频可以收藏到多个收藏夹中
     *
     * @param userId
     * @param videoId
     * @param folderIds
     * @return
     */
    List<VmsUserFavoriteFolderVideo> getListByUserIdVideoIdFolderIds(Integer userId, Integer videoId,
                                                               List<Integer> folderIds);

    /**
     * 根据用户id,和视频id,获取到其所有收藏记录
     * @param userId
     * @param videoId
     * @return
     */
    List<VmsUserFavoriteFolderVideo> getListByUserIdVideoId(Integer userId, Integer videoId);

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
     * @param ids
     * @param curFolderId
     * @param newFolderIds
     * @return
     */
    boolean copyBatchVideoToOtherFolders(List<Integer> ids, Integer curFolderId, List<Integer> newFolderIds);


    /**
     * 根据ids批量获取记录
     * @param ids
     * @return
     */
    List<VmsUserFavoriteFolderVideo> getBatchByIds(List<Integer> ids);

    /**
     * 移动当前收藏夹视频到其他收藏夹
     * @param ids
     * @param curFolderId
     * @param newFolderId
     * @return
     */
    boolean moveBatchVideoToOtherFolder(List<Integer> ids, Integer curFolderId, Integer newFolderId);
}
