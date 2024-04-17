package com.moncoder.lingo.video.client;

import com.moncoder.lingo.common.api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Moncoder
 * @version 1.0
 * @description 阿里云文件服务rpc调用
 * @date 2024/4/17 15:25
 */
@FeignClient("lingo-oss")
public interface OssClient {

    /**
     * 上传视频文件
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/oss/upload/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<String> uploadVideo(@RequestPart MultipartFile file);
    /**
     * 上传视频缩略图
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/oss/upload/video/thumbnail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<String> uploadVideoThumbnail(@RequestPart MultipartFile file);
}
