package com.moncoder.lingo.video.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsUserFavoriteFolderVideo;
import com.moncoder.lingo.mapper.VmsUserFavoriteFolderVideoMapper;
import com.moncoder.lingo.video.domain.dto.FolderVideoCopyDTO;
import com.moncoder.lingo.video.domain.dto.FolderVideoMoveDTO;
import com.moncoder.lingo.video.service.IVmsUserFavoriteFolderVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;



import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * <p>
 * 用户收藏视频记录表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-01 13:14:15
 */
@Service
public class VmsUserFavoriteFolderVideoServiceImpl
        extends ServiceImpl<VmsUserFavoriteFolderVideoMapper, VmsUserFavoriteFolderVideo>
        implements IVmsUserFavoriteFolderVideoService {


    @Autowired
    private VmsUserFavoriteFolderVideoMapper favoriteVideoMapper;

    @Override
    public List<VmsUserFavoriteFolderVideo> getListByUserIdVideoIdFolderIds(Integer userId, Integer videoId,
                                                                      List<Integer> folderIds) {
        // 如果folderIds为空，直接返回空列表
        if (CollUtil.isEmpty(folderIds)) {
            return Collections.emptyList();
        }
        // 注意过滤掉null，要不然后续会被收集起来
        return folderIds.stream().map(folderId ->
                        lambdaQuery().eq(VmsUserFavoriteFolderVideo::getUserId, userId)
                                .eq(VmsUserFavoriteFolderVideo::getVideoId, videoId)
                                .eq(VmsUserFavoriteFolderVideo::getFolderId, folderId)
                                .one())
                // 过滤掉为null的元素
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<VmsUserFavoriteFolderVideo> getAllByUserIdVideoId(Integer userId, Integer videoId) {
        // 一个用户的一个视频可以收藏到多个收藏夹中
        return lambdaQuery().eq(VmsUserFavoriteFolderVideo::getUserId, userId)
                .eq(VmsUserFavoriteFolderVideo::getVideoId, videoId)
                .list();
    }


    @Override
    public List<VmsUserFavoriteFolderVideo> getAllByUserIdFolderId(Integer userId, Integer folderId) {
        // 一个用户的一个收藏夹可以有多个视频
        return lambdaQuery().eq(VmsUserFavoriteFolderVideo::getUserId, userId)
                .eq(VmsUserFavoriteFolderVideo::getFolderId, folderId)
                .list();
    }

    @Override
    public boolean saveBatchByUserIdVideoIdFolderIds(Integer userId, Integer videoId, List<Integer> folderIds) {
        // 将视频收藏到多个收藏夹中
        List<VmsUserFavoriteFolderVideo> favoriteVideoList = folderIds.stream().map(folderId -> {
            VmsUserFavoriteFolderVideo favoriteVideo = new VmsUserFavoriteFolderVideo();
            favoriteVideo.setVideoId(videoId);
            favoriteVideo.setFolderId(folderId);
            return favoriteVideo;
        }).collect(Collectors.toList());
        return saveBatch(favoriteVideoList);
    }

    @Override
    public boolean copyVideosToFolders(FolderVideoCopyDTO folderVideoCopyDTO) {
        Integer userId = folderVideoCopyDTO.getUserId();
        List<Integer> videoIds = folderVideoCopyDTO.getVideoIds();
        Integer curFolderId = folderVideoCopyDTO.getCurFolderId();
        List<Integer> newFolderIds = folderVideoCopyDTO.getNewFolderIds();

        // 复制视频到新收藏夹
        List<VmsUserFavoriteFolderVideo> flatList = newFolderIds.stream()
                // 不需要复制到当前收藏夹
                .filter(newFolderId -> !newFolderId.equals(curFolderId))
                .flatMap(newFolderId -> copyVideosToFolder(videoIds, userId, newFolderId))
                .collect(Collectors.toList());

        // 保存视频
        return saveBatch(flatList);
    }

    private Stream<VmsUserFavoriteFolderVideo> copyVideosToFolder(List<Integer> videoIds,
                                                                  Integer userId,
                                                                  Integer folderId) {
        return videoIds.stream()
                .map(videoId -> {
                    // 判断收藏夹是否有该视频，如果有则不需要复制
                    Long count = lambdaQuery().eq(VmsUserFavoriteFolderVideo::getUserId, userId)
                            .eq(VmsUserFavoriteFolderVideo::getVideoId, videoId)
                            .eq(VmsUserFavoriteFolderVideo::getFolderId, folderId)
                            .count();
                    if(count == 0){
                        VmsUserFavoriteFolderVideo modifiedVideo = new VmsUserFavoriteFolderVideo();
                        modifiedVideo.setUserId(userId);
                        modifiedVideo.setVideoId(videoId);
                        modifiedVideo.setFolderId(folderId);
                        modifiedVideo.setCreateTime(LocalDateTime.now());
                        // 返回修改后的视频
                        return modifiedVideo;

                    }
                    return null;
                })
                .filter(Objects::nonNull);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean moveVideosToFolder(FolderVideoMoveDTO folderVideoMoveDTO) {
        //1. 判断两个收藏夹是否相同
        var curFolderId = folderVideoMoveDTO.getCurFolderId();
        var newFolderId = folderVideoMoveDTO.getNewFolderId();
        if (curFolderId.equals(newFolderId)) {
            return false;
        }

        var userId = folderVideoMoveDTO.getUserId();
        var videoIds = folderVideoMoveDTO.getVideoIds();

        // 2. 删除旧收藏夹中的视频
        lambdaUpdate().eq(VmsUserFavoriteFolderVideo::getUserId, userId)
                .eq(VmsUserFavoriteFolderVideo::getFolderId, curFolderId)
                .in(VmsUserFavoriteFolderVideo::getVideoId, videoIds)
                .remove();

        // 3. 向新收藏夹添加视频
        List<VmsUserFavoriteFolderVideo> videosToAdd = new ArrayList<>();
        for (Integer videoId : videoIds) {
            // 判断新收藏夹中是否已经存在该视频ID
            boolean existsInNewFolder = lambdaQuery().eq(VmsUserFavoriteFolderVideo::getUserId, userId)
                    .eq(VmsUserFavoriteFolderVideo::getVideoId, videoId)
                    .eq(VmsUserFavoriteFolderVideo::getFolderId, newFolderId)
                    .count() > 0;

            // 如果新收藏夹中不存在该视频ID，则添加到待添加列表中
            if (!existsInNewFolder) {
                var folderVideo = new VmsUserFavoriteFolderVideo();
                folderVideo.setUserId(userId);
                folderVideo.setVideoId(videoId);
                folderVideo.setFolderId(newFolderId);
                folderVideo.setCreateTime(LocalDateTime.now());
                videosToAdd.add(folderVideo);
            }
        }

        return saveBatch(videosToAdd);
    }



}
