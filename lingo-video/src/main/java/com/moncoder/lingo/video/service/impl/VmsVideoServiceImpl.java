package com.moncoder.lingo.video.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.constant.VideoConstant;
import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.common.util.FileUtil;
import com.moncoder.lingo.entity.*;
import com.moncoder.lingo.mapper.VmsVideoMapper;
import com.moncoder.lingo.video.domain.dto.VideoCreateDTO;
import com.moncoder.lingo.video.domain.vo.UploadVideoVo;
import com.moncoder.lingo.video.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    private IVmsUserFavoriteFolderVideoService favoriteFolderVideoService;
    @Autowired
    private IVmsVideoLikeService videoLikeService;
    @Autowired
    private VmsVideoMapper videoMapper;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IVmsHomeLatestVideoService latestVideoService;
    @Autowired
    private IVmsHomeTrendingVideoService trendingVideoService;
    @Autowired
    private IVmsHomeRecommendedVideoService recommendedVideoService;

    @Override
    public UploadVideoVo uploadVideo(MultipartFile file) {
        // 1.上传视频到指定文件夹中
        String videoUrl = "";
        try {
            videoUrl = FileUtil.saveFile(file, VideoConstant.VMS_VIDEO_PATH);
        } catch (IOException e) {
            throw new ApiException("视频上传失败！");
        }
        // 2.生成缩率图
        String thumbnailUrl = generateThumbnail(file);
        // 3.返回存储url
        UploadVideoVo uploadVideoVo = new UploadVideoVo();
        uploadVideoVo.setVideoUrl(videoUrl);
        uploadVideoVo.setThumbnailUrl(thumbnailUrl);
        return uploadVideoVo;
    }

    private String generateThumbnail(MultipartFile file) {
        return "thumbnailUrl";
    }

    @Override
    public boolean saveVideo(VideoCreateDTO videoCreateDTO) {
        VmsVideo video = new VmsVideo();
        BeanUtils.copyProperties(videoCreateDTO, video);
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
                favoriteFolderVideoService.getListByUserIdVideoId(userId, videoId);

        // 4.如果收藏视频表没有记录且收藏夹id列表不为空，则为收藏
        if (favoriteVideoList != null && favoriteVideoList.isEmpty() && !folderIds.isEmpty()) {
            // 收藏到收藏视频记录表
            favoriteFolderVideoService.saveBatchByUserIdVideoIdFolderIds(userId, videoId, folderIds);
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
                favoriteFolderVideoService.saveBatchByUserIdVideoIdFolderIds(userId, videoId, addedFolderIds);
            }
            // 处理取消的收藏夹
            if (!removedFolderIds.isEmpty()) {
                List<Integer> favoriteVideoIds = favoriteVideoList.stream().filter(favoriteVideo ->
                                removedFolderIds.contains(favoriteVideo.getFolderId()))
                        .map(VmsUserFavoriteFolderVideo::getId)
                        .collect(Collectors.toList());
                favoriteFolderVideoService.removeByIds(favoriteVideoIds);
            }
        }

        // 6.如果收藏视频表有记录且收藏夹id列表为空，则为取消收藏
        if (favoriteVideoList != null && !favoriteVideoList.isEmpty() && folderIds.isEmpty()) {
            List<Integer> favoriteVideoIds = favoriteVideoList.stream().map(VmsUserFavoriteFolderVideo::getId)
                    .collect(Collectors.toList());
            favoriteFolderVideoService.removeByIds(favoriteVideoIds);
            video.setFavorites(favorites - 1);
            return updateById(video);
        }

        // 默认返回失败
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean likeVideo(Integer userId, Integer videoId) {
        // 1.查询当前视频点赞数
        VmsVideo video = lambdaQuery().eq(VmsVideo::getId, videoId).one();
        Integer likes = video.getLikes();
        // 2.查看点赞记录表是否有记录
        VmsVideoLike videoLike = videoLikeService.getByUserIdAndVideoId(userId, videoId);
        if (videoLike == null) {
            // 2.1 点赞
            VmsVideoLike newVideoLike = new VmsVideoLike();
            newVideoLike.setUserId(userId);
            newVideoLike.setVideoId(videoId);
            newVideoLike.setCreateTime(LocalDateTime.now());
            videoLikeService.save(newVideoLike);
            // 当前视频点赞数 + 1
            video.setLikes(likes + 1);
        } else {
            // 2.2 取消点赞
            videoLikeService.removeById(videoLike.getId());
            // 当前视频点赞数 - 1
            video.setLikes(likes - 1);
        }
        // 3.修改点赞数
        return updateById(video);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveLatestVideos(Integer videoNum) {
        // 1.参数验证
        if (videoNum == null || videoNum <= 0) {
            throw new IllegalArgumentException("参数有误！");
        }
        // 2.获取最新视频并保存
        return saveVideosHelper(videoNum, videoMapper::selectLatestVideos,
                video -> {
                    VmsHomeLatestVideo homeLatestVideo = new VmsHomeLatestVideo();
                    homeLatestVideo.setVideoId(video.getId());
                    BeanUtils.copyProperties(video, homeLatestVideo);
                    return homeLatestVideo;
                }, latestVideoService, VideoConstant.VMS_HOME_LATEST_VIDEO_KEY);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveTrendingVideos(Integer videoNum) {
        // 1.参数验证
        if (videoNum == null || videoNum <= 0) {
            throw new IllegalArgumentException("参数有误！");
        }
        // 2.获取热门视频并保存
        return saveVideosHelper(videoNum, videoMapper::selectTrendingVideos,
                video -> {
                    VmsHomeTrendingVideo homeTrendingVideo = new VmsHomeTrendingVideo();
                    homeTrendingVideo.setVideoId(video.getId());
                    BeanUtils.copyProperties(video, homeTrendingVideo);
                    return homeTrendingVideo;
                }, trendingVideoService, VideoConstant.VMS_HOME_TRENDING_VIDEO_KEY);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRecommendedVideos(Integer videoNum) {
        // 1.参数验证
        if (videoNum == null || videoNum <= 0) {
            throw new IllegalArgumentException("参数有误！");
        }
        // 2.获取推荐视频并保存
        return saveVideosHelper(videoNum, videoMapper::selectRecommendedVideos,
                video -> {
                    VmsHomeRecommendedVideo homeRecommendedVideo = new VmsHomeRecommendedVideo();
                    homeRecommendedVideo.setVideoId(video.getId());
                    BeanUtils.copyProperties(video, homeRecommendedVideo);
                    return homeRecommendedVideo;
                }, recommendedVideoService, VideoConstant.VMS_HOME_RECOMMENDED_VIDEO_KEY);
    }

    /**
     * 保存视频的通用辅助方法
     *
     * @param videoNum        视频数目
     * @param selectFunction  用于从数据库中选择视频的函数
     * @param mappingFunction 用于将数据库视频映射为目标实体的函数
     * @param service         数据库服务对象
     * @param redisKey        缓存键值
     * @param <S>             业务接口类型模板
     * @param <T>             实体类型模板
     * @return 是否成功保存视频
     */
    public <S extends IService<T>, T> boolean saveVideosHelper(Integer videoNum,
                                                               Function<Integer, List<VmsVideo>> selectFunction,
                                                               Function<VmsVideo, T> mappingFunction,
                                                               S service, String redisKey) {
        // 1.参数验证
        if (videoNum == null || videoNum <= 0 || mappingFunction == null || service == null || redisKey == null) {
            throw new IllegalArgumentException("参数有误！");
        }
        // 2.获取需要的视频
        List<VmsVideo> videos = selectFunction.apply(videoNum);
        // 3.检查视频是否为空
        if (videos == null || videos.isEmpty()) {
            throw new ApiException("未找到符合条件的视频！");
        }
        // 4.转换视频对象
        List<T> targetVideos = videos.stream().map(mappingFunction).collect(Collectors.toList());
        // 5.保存到数据库
        service.saveBatch(targetVideos);
        // 6.保存到缓存
        Map<String, Object> map = new HashMap<>(videoNum);
        videos.forEach(video -> map.put(video.getId().toString(), video));
        redisService.putAll(redisKey, map);
        redisService.expire(redisKey, VideoConstant.VMS_HOME_LATEST_VIDEO_EXPIRE);
        return true;
    }

}
