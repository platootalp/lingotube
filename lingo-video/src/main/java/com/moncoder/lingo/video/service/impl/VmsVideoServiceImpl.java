package com.moncoder.lingo.video.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.entity.VmsUserFavoriteFolder;
import com.moncoder.lingo.entity.VmsUserFavoriteFolderVideo;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.mapper.VmsVideoMapper;
import com.moncoder.lingo.video.domain.dto.VideoCreateDTO;
import com.moncoder.lingo.video.service.IVmsUserFavoriteFolderService;
import com.moncoder.lingo.video.service.IVmsUserFavoriteFolderVideoService;
import com.moncoder.lingo.video.service.IVmsVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Service
public class VmsVideoServiceImpl extends ServiceImpl<VmsVideoMapper, VmsVideo> implements IVmsVideoService {

    @Autowired
    private IVmsUserFavoriteFolderService favoriteFolderService;
    @Autowired
    private IVmsUserFavoriteFolderVideoService folderVideoService;

    @Override
    public boolean uploadVideo(VideoCreateDTO vmsVideoDTO) {
        // 1.视频保存到服务器，返回视频路径
        // 2.生成视频缩略图，返回缩略图路径
        // 3.保存视频实体到数据库中
        VmsVideo video = new VmsVideo();
        BeanUtils.copyProperties(vmsVideoDTO, video);
        return save(video);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean favoriteVideo(Integer userId, Integer videoId, List<Integer> folderIds) {
        // 1.查看视频是否存在
        VmsVideo video = lambdaQuery().eq(VmsVideo::getId, videoId).one();
        if (video == null) {
            throw new ApiException("视频不存在！");
        }
        // 获取点赞数
        Integer favorites = video.getFavorites();

        // 2.查看收藏夹是都存在
        folderIds.stream().forEach(folderId -> {
            boolean exists = favoriteFolderService.lambdaQuery()
                    .eq(VmsUserFavoriteFolder::getId, folderId)
                    .exists();
            if (!exists) {
                throw new ApiException("收藏夹不存在！");
            }
        });

        // 3.查看收藏视频表是否有记录
        List<VmsUserFavoriteFolderVideo> favoriteVideoList =
                folderVideoService.getListByUserIdVideoId(userId, videoId);

        // 4.如果收藏视频表没有记录且收藏夹id列表不为空，则为收藏
        if (favoriteVideoList != null && favoriteVideoList.isEmpty() && !folderIds.isEmpty()) {
            // 收藏到收藏视频记录表
            folderVideoService.saveBatchByUserIdVideoIdFolderIds(userId, videoId, folderIds);
            // 当前视频收藏数+1
            video.setFavorites(favorites + 1);
            return updateById(video);
        }

        // 5.处理新增和取消的收藏夹
        if (!CollUtil.isEmpty(favoriteVideoList)) {
            List<Integer> oldFolderIds = favoriteVideoList.stream().map(VmsUserFavoriteFolderVideo::getFolderId)
                    .collect(Collectors.toList());
            List<Integer> addedFolderIds = folderIds.stream()
                    .filter(folderId -> !oldFolderIds.contains(folderId))
                    .collect(Collectors.toList());
            List<Integer> removedFolderIds = oldFolderIds.stream()
                    .filter(folderId -> !folderIds.contains(folderId))
                    .collect(Collectors.toList());
            // 处理新增的收藏夹
            if (!addedFolderIds.isEmpty()) {
                folderVideoService.saveBatchByUserIdVideoIdFolderIds(userId, videoId, addedFolderIds);
            }
            // 处理取消的收藏夹
            if (!removedFolderIds.isEmpty()) {
                List<Integer> favoriteVideoIds = favoriteVideoList.stream().filter(favoriteVideo ->
                                removedFolderIds.contains(favoriteVideo.getFolderId()))
                        .map(VmsUserFavoriteFolderVideo::getId)
                        .collect(Collectors.toList());
                folderVideoService.removeByIds(favoriteVideoIds);
            }
        }

        // 6.如果收藏视频表有记录且收藏夹id列表为空，则为取消收藏
        if (favoriteVideoList != null && !favoriteVideoList.isEmpty() && folderIds.isEmpty()) {
            List<Integer> favoriteVideoIds = favoriteVideoList.stream().map(VmsUserFavoriteFolderVideo::getId)
                    .collect(Collectors.toList());
            folderVideoService.removeByIds(favoriteVideoIds);
            video.setFavorites(favorites - 1);
            return updateById(video);
        }

        // 默认返回失败
        return true;
    }

}
