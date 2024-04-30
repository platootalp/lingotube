package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.vo.VideoLikeVO;
import com.moncoder.lingo.video.service.IVmsVideoLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 赞过的视频表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 19:07:44
 */
@Api(tags = "赞过的视频管理")
@RestController
@RequestMapping("/video/like")
public class VmsVideoLikeController {

    @Autowired
    private IVmsVideoLikeService videoLikeService;


    @ApiOperation("查看点赞记录是否存在")
    @GetMapping("/exist")
    public Result<Boolean> exist(@RequestParam @NotNull Integer userId,
                                 @RequestParam @NotNull Integer videoId) {
        return Result.success(videoLikeService.exist(userId, videoId));
    }


    @ApiOperation("点赞、取消点赞视频")
    @PostMapping("/save")
    public Result<String> likeVideo(@RequestParam @NotNull Integer userId,
                                    @RequestParam @NotNull Integer videoId) {
        boolean flag = videoLikeService.likeVideo(userId, videoId);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取用户全部点赞记录")
    @GetMapping("/list")
    public Result<List<VideoLikeVO>> getList(@RequestParam @NotNull Integer userId,
                                              @RequestParam(required = false) String titleKeyWord) {
        List<VideoLikeVO> videoLikeVOS =
                videoLikeService.getListByUserId(userId, titleKeyWord);
        return Result.success(videoLikeVOS);
    }


    @ApiOperation("删除点赞记录")
    @DeleteMapping("")
    public Result<String> deleteOne(@RequestParam @NotNull Integer userId,
                                    @RequestParam @NotNull Integer videoId) {
        boolean flag = videoLikeService.deleteOne(userId, videoId);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("清空点赞记录")
    @DeleteMapping("/all")
    public Result<Integer> clear(@RequestParam @NotNull Integer userId) {
        int num = videoLikeService.clear(userId);
        return Result.success(num);
    }

    @ApiOperation("批量删除点赞记录")
    @DeleteMapping("/s")
    public Result<String> deleteBatch(@RequestParam @NotNull Integer userId,
                                      @RequestParam @NotNull List<Integer> ids) {
        boolean flag = videoLikeService.deleteBatch(userId, ids);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }


    @ApiOperation("获取用户全部点赞记录（分页）")
    @GetMapping("/page")
    public Result<LPage<VideoLikeVO>> getPage(@RequestParam @NotNull Integer userId,
                                              @RequestParam(defaultValue = "1") Long pageNum,
                                              @RequestParam(defaultValue = "5") Long pageSize,
                                              @RequestParam(required = false) String titleKeyWord) {
        LPage<VideoLikeVO> videoLikeVoPage =
                videoLikeService.getPageByUserId(userId, pageNum, pageSize, titleKeyWord);
        return Result.success(videoLikeVoPage);
    }
}
