package com.moncoder.lingo.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.admin.client.OssClient;
import com.moncoder.lingo.admin.client.UserClient;
import com.moncoder.lingo.admin.client.VideoClient;
import com.moncoder.lingo.admin.domain.dto.VideoCreateDTO;
import com.moncoder.lingo.admin.domain.vo.UserShowInfoVO;
import com.moncoder.lingo.admin.service.IVmsVideoService;
import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.entity.*;
import com.moncoder.lingo.mapper.VmsVideoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private OssClient ossClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private VideoClient videoClient;

    @Override
    public String uploadVideo(MultipartFile videoFile) {
        return ossClient.uploadVideo(videoFile).getData();
    }

    @Override
    public String uploadVideoThumbnail(MultipartFile thumbnailFile) {
        return ossClient.uploadVideoThumbnail(thumbnailFile).getData();
    }

    @Override
    public boolean saveVideo(VideoCreateDTO videoCreateDTO) {
        Integer categoryId = videoCreateDTO.getCategoryId();
        Integer levelId = videoCreateDTO.getLevelId();
        Integer uploaderId = videoCreateDTO.getUploaderId();

        String categoryName = videoClient.getCategoryName(categoryId).getData();
        String levelName = videoClient.getLevelName(levelId).getData();
        UserShowInfoVO userShowInfo = userClient.getUserShowInfo(uploaderId).getData();

        VmsVideo video = new VmsVideo();
        BeanUtils.copyProperties(videoCreateDTO, video);
        video.setCategoryName(categoryName);
        video.setLevelName(levelName);
        video.setUploaderNickname(userShowInfo.getNickname());
        video.setUploaderAvatar(userShowInfo.getAvatar());
        return save(video);
    }

}
