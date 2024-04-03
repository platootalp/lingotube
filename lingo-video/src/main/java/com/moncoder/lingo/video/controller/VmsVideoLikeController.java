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
@RequestMapping("/video/like/log")
public class VmsVideoLikeController {

    @Autowired
    private IVmsVideoLikeService videoLikeService;

    @ApiOperation("批量删除赞过的视频记录")
    @DeleteMapping("/s")
    public Result<String> deleteBatch(@RequestParam @NotNull Integer userId,
                                      @RequestParam @NotNull List<Integer> ids) {
        boolean flag = videoLikeService.deleteBatch(userId, ids);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }


    @ApiOperation("查询全部稍后再看记录")
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
