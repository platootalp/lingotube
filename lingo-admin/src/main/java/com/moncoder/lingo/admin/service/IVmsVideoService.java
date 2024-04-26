package com.moncoder.lingo.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.admin.domain.dto.VideoCreateDTO;
import com.moncoder.lingo.entity.VmsVideo;
import org.springframework.web.multipart.MultipartFile;

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
     * 上传视频
     *
     * @param videoFile
     * @return
     */
    String uploadVideo(MultipartFile videoFile);

    /**
     * 上传视频缩略图
     * @param thumbnailFile
     * @return
     */
    String uploadVideoThumbnail(MultipartFile thumbnailFile);

    /**
     * 保存视频
     *
     * @param videoCreateDTO
     * @return
     */
    boolean saveVideo(VideoCreateDTO videoCreateDTO);

}
