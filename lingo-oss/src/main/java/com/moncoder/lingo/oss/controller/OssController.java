package com.moncoder.lingo.oss.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.common.constant.UserConstant;
import com.moncoder.lingo.common.constant.VideoConstant;
import com.moncoder.lingo.oss.service.IOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    public Result<String> uploadVideo(@RequestPart("file") @NotNull MultipartFile file) {
        String videoUrl = ossService.upload(file, VideoConstant.VMS_VIDEO_PREFIX);
        return Result.success(videoUrl);
    }

    @ApiOperation("批量上传视频")
    @PostMapping("/upload/video/s")
    public Result<List<String>> uploadBatchVideo(@RequestPart("fileList") @NotNull List<MultipartFile> fileList) {
        List<String> videoUrls = ossService.uploadBatch(fileList, VideoConstant.VMS_VIDEO_PREFIX);
        return Result.success(videoUrls);
    }

    @ApiOperation("删除视频")
    @DeleteMapping("/video")
    public Result<String> deleteVideo(@RequestParam @NotNull String url) {
        ossService.delete(url);
        return Result.success();
    }

    @ApiOperation("上传视频缩略图")
    @PostMapping("/upload/video/thumbnail")
    public Result<String> uploadVideoThumbnail(@RequestPart("file") @NotNull MultipartFile file) {
        String videoUrl = ossService.upload(file, VideoConstant.VMS_VIDEO_THUMBNAIL_PREFIX);
        return Result.success(videoUrl);
    }

    @ApiOperation("删除视频缩略图")
    @DeleteMapping("video/thumbnail")
    public Result<String> deleteVideoThumbnail(@RequestParam @NotNull String url) {
        ossService.delete(url);
        return Result.success();
    }

    @ApiOperation("上传用户头像")
    @PostMapping("/upload/user/avatar")
    public Result<String> uploadUserAvatar(@RequestPart("file") @NotNull MultipartFile file) {
        String videoUrl = ossService.upload(file, UserConstant.UMS_USER_AVATAR_PREFIX);
        return Result.success(videoUrl);
    }

    @ApiOperation("删除头像")
    @DeleteMapping("/user/avatar")
    public Result<String> deleteUserAvatar(@RequestParam @NotNull String url) {
        ossService.delete(url);
        return Result.success();
    }

    @ApiOperation("上传二维码")
    @PostMapping("/upload/qrcode")
    public Result<String> uploadQRCode(@RequestPart("file") @NotNull MultipartFile file) {
        String videoUrl = ossService.upload(file, UserConstant.UMS_QRCODE_PREFIX);
        return Result.success(videoUrl);
    }
}
