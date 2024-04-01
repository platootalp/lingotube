package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsUserFavoriteFolderVideo;
import com.moncoder.lingo.video.domain.dto.FolderVideoCopyDTO;
import com.moncoder.lingo.video.domain.dto.FolderVideoMoveDTO;

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
    List<VmsUserFavoriteFolderVideo> getListByUserIdVideoIdFolderIds(Integer userId, Integer videoId,
                                                                     List<Integer> folderIds);

    /**
     * 根据用户id和视频id,获取到所有收藏夹中的记录
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
    boolean copyVideosToFolders(FolderVideoCopyDTO folderVideoCopyDTO);


    /**
     * 移动当前收藏夹视频到其他收藏夹
     *
     * @param folderVideoMoveDTO
     * @return
     */
    boolean moveVideosToFolder(FolderVideoMoveDTO folderVideoMoveDTO);
}
