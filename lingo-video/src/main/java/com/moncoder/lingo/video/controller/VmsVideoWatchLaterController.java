package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.dto.VideoWatchLaterDTO;
import com.moncoder.lingo.video.domain.vo.VideoWatchLaterVO;
import com.moncoder.lingo.video.service.IVmsVideoWatchLaterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 视频稍后再看表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 15:46:27
 */
@Api(tags = "视频稍后再看管理")
@RestController
@RequestMapping("/video/watchLater")
public class VmsVideoWatchLaterController {
    @Autowired
    private IVmsVideoWatchLaterService videoWatchLaterService;

    @ApiOperation("查看稍后再看记录是否存在")
    @GetMapping("/exist")
    public Result<Boolean> exist(@RequestParam @NotNull Integer userId,
                                 @RequestParam @NotNull Integer videoId) {
        return Result.success(videoWatchLaterService.exist(userId, videoId));
    }

    @ApiOperation("保存稍后再看记录")
    @PostMapping("/save")
    public Result<String> save(@RequestBody @Valid VideoWatchLaterDTO videoWatchLaterDTO) {
        boolean flag = videoWatchLaterService.save(videoWatchLaterDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取用户全部稍后再看记录")
    @GetMapping("/list")
    public Result<List<VideoWatchLaterVO>> getList(
            @ApiParam(value = "用户ID", required = true) @RequestParam @NotNull Integer userId,
            @ApiParam(value = "排序方式。可选值：1-按添加时间（从新到旧），2-按添加时间（从旧到新），" +
                    "3-热度最高，4-按上传时间（从新到旧），5-按上传时间（从旧到新）", defaultValue = "1")
            @RequestParam(defaultValue = "1") @NotNull Integer sort) {
        List<VideoWatchLaterVO> watchHistoryVOs
                = videoWatchLaterService.getListByUserId(userId, sort);
        return Result.success(watchHistoryVOs);
    }

    @ApiOperation("删除已观看记录")
    @DeleteMapping("/watched")
    public Result<String> deleteWatched(@RequestParam @NotNull Integer userId) {
        boolean flag = videoWatchLaterService.deleteWatch(userId);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("删除稍后再看记录")
    @DeleteMapping("")
    public Result<String> deleteOne(@RequestParam @NotNull Integer userId,
                                    @RequestParam @NotNull Integer videoId) {
        boolean flag = videoWatchLaterService.deleteOne(userId, videoId);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("批量删除稍后再看记录")
    @DeleteMapping("/s")
    public Result<String> deleteBatch(@RequestParam @NotNull Integer userId,
                                      @RequestParam @NotNull List<Integer> videoIds) {
        boolean flag = videoWatchLaterService.deleteBatch(userId, videoIds);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("删除全部稍后再看记录")
    @DeleteMapping("/all")
    public Result<String> deleteAll(@RequestParam @NotNull Integer userId) {
        boolean flag = videoWatchLaterService.deleteAll(userId);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("查询全部稍后再看记录")
    @GetMapping("/page")
    public Result<LPage<VideoWatchLaterVO>> getPage(@RequestParam @NotNull Integer userId,
                                                    @RequestParam(defaultValue = "1") Long pageNum,
                                                    @RequestParam(defaultValue = "5") Long pageSize,
                                                    @RequestParam(required = false) String titleKeyWord) {
        LPage<VideoWatchLaterVO> videoWatchLaterVOLPage
                = videoWatchLaterService.getPageByUserId(userId, pageNum, pageSize, titleKeyWord);
        return Result.success(videoWatchLaterVOLPage);
    }
}
