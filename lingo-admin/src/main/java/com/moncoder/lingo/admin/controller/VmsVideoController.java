package com.moncoder.lingo.admin.controller;

import com.moncoder.lingo.admin.domain.dto.VideoCreateDTO;
import com.moncoder.lingo.admin.service.IVmsVideoService;
import com.moncoder.lingo.common.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * <p>
 * 视频表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Api(tags = "视频后台管理")
@RestController
@RequestMapping("/admin/video")
public class VmsVideoController {

    @Autowired
    private IVmsVideoService videoService;

    @ApiOperation("上传视频")
    @PostMapping("/upload")
    public Result<String> uploadVideo(@RequestParam("file") MultipartFile videoFile) {
        String videoUrl = videoService.uploadVideo(videoFile);
        return Result.success(videoUrl);
    }

    @ApiOperation("上传视频缩略图")
    @PostMapping("/thumbnail/upload")
    public Result<String> uploadVideoThumbnail(@RequestParam("file") MultipartFile thumbnailFile) {
        String thumbnailUrl = videoService.uploadVideoThumbnail(thumbnailFile);
        return Result.success(thumbnailUrl);
    }

    @ApiOperation("保存视频信息")
    @PostMapping("/save")
    public Result<String> saveVideo(@RequestBody @Valid VideoCreateDTO videoCreateDTO) {
        boolean flag = videoService.saveVideo(videoCreateDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

}
