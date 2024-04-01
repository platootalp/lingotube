package com.moncoder.lingo.video.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsUserFavoriteFolderVideo;
import com.moncoder.lingo.mapper.VmsUserFavoriteFolderVideoMapper;
import com.moncoder.lingo.mapper.VmsUserFavoriteVideoMapper;
import com.moncoder.lingo.video.service.IVmsUserFavoriteFolderVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户收藏视频记录表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-01 13:14:15
 */
@Service
public class VmsUserFavoriteFolderVideoServiceImpl extends ServiceImpl<VmsUserFavoriteFolderVideoMapper, VmsUserFavoriteFolderVideo> implements IVmsUserFavoriteFolderVideoService {

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
    public List<VmsUserFavoriteFolderVideo> getListByUserIdVideoId(Integer userId, Integer videoId) {
        return lambdaQuery().eq(VmsUserFavoriteFolderVideo::getUserId, userId)
                .eq(VmsUserFavoriteFolderVideo::getVideoId, videoId)
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
    public boolean copyBatchVideoToOtherFolders(List<Integer> ids, Integer curFolderId, List<Integer> newFolderIds) {
        // 获取全部记录
        List<VmsUserFavoriteFolderVideo> favoriteVideoList = getBatchByIds(ids);
        // 将这些视频的收藏夹id改为newFolderIds‘
        List<List<VmsUserFavoriteFolderVideo>> lists = newFolderIds.stream()
                .map(newFolderId -> {
                    List<VmsUserFavoriteFolderVideo> modifiedList = favoriteVideoList.stream()
                            .map(favoriteVideo -> {
                                VmsUserFavoriteFolderVideo modifiedVideo = new VmsUserFavoriteFolderVideo();
                                modifiedVideo.setFolderId(newFolderId);
                                modifiedVideo.setCreateTime(LocalDateTime.now());
                                return modifiedVideo;
                            })
                            .collect(Collectors.toList());
                    return modifiedList;
                })
                .collect(Collectors.toList());

        List<VmsUserFavoriteFolderVideo> flatList = lists.stream()
                // 将二维列表映射为一个流
                .flatMap(List::stream)
                // 收集为一维列表
                .collect(Collectors.toList());
        return saveBatch(flatList);
    }

    @Override
    public List<VmsUserFavoriteFolderVideo> getBatchByIds(List<Integer> ids) {
        return favoriteVideoMapper.selectBatchIds(ids);
    }

    @Override
    public boolean moveBatchVideoToOtherFolder(List<Integer> ids, Integer curFolderId, Integer newFolderId) {
        // 获取新收藏夹所有视频
        return false;
    }

}
