package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.dto.VideoCommentDTO;
import com.moncoder.lingo.video.service.IVmsVideoCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Api("评论管理")
@RestController
@RequestMapping("/video/comment")
public class VmsVideoCommentController {

    @Autowired
    private IVmsVideoCommentService videoCommentService;

    @ApiOperation("评论视频")
    @PostMapping("/add")
    public Result<String> commentVideo(@RequestParam @NotNull Integer userId,
                                       @RequestParam @NotNull Integer videoId,
                                       @RequestBody @Valid VideoCommentDTO videoCommentDTO) {
        boolean flag = videoCommentService.commentVideo(userId, videoId, videoCommentDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }
}
