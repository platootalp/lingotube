package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.dto.VideoCreateDTO;
import com.moncoder.lingo.video.service.IVmsVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 视频表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Api(tags = "视频管理")
@RestController
@RequestMapping("/video")
public class VmsVideoController {

    @Autowired
    private IVmsVideoService videoService;

    // TODO 请求体中加入视频文件
    @ApiOperation("上传视频")
    @PostMapping("/upload")
    public Result<Void> uploadVideo(@RequestBody @Valid VideoCreateDTO vmsVideoDTO) {
        boolean flag = videoService.uploadVideo(vmsVideoDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("收藏、取消收藏视频")
    @PostMapping("/favorite")
    public Result<String> favoriteVideo(@RequestParam @NotNull Integer userId,
                                        @RequestParam @NotNull Integer videoId,
                                        @RequestParam @NotNull List<Integer> folderIds) {
        boolean flag = videoService.favoriteVideo(userId, videoId, folderIds);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }
}
