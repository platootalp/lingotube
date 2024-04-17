package com.moncoder.lingo.oss.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.common.constant.UserConstant;
import com.moncoder.lingo.common.constant.VideoConstant;
import com.moncoder.lingo.oss.service.IOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author Moncoder
 * @version 1.0
 * @description 阿里云文件存储控制器
 * @date 2024/4/15 21:24
 */
@Api(tags = "阿里云文件存储控制器")
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private IOssService ossService;

    @ApiOperation("上传视频")
    @PostMapping("/upload/video")
    public Result<String> uploadVideo(@RequestParam @NotNull MultipartFile file) {
        String videoUrl = ossService.upload(file, VideoConstant.VMS_VIDEO_PREFIX);
        return Result.success(videoUrl);
    }

    @ApiOperation("上传视频缩略图")
    @PostMapping("/upload/video/thumbnail")
    public Result<String> uploadVideoThumbnail(@RequestParam @NotNull MultipartFile file) {
        String videoUrl = ossService.upload(file, VideoConstant.VMS_VIDEO_THUMBNAIL_PREFIX);
        return Result.success(videoUrl);
    }

    @ApiOperation("上传用户头像")
    @PostMapping("/upload/user/avatar")
    public Result<String> uploadUserAvatar(@RequestParam @NotNull MultipartFile file) {
        String videoUrl = ossService.upload(file, UserConstant.UMS_USER_AVATAR_PREFIX);
        return Result.success(videoUrl);
    }
}
