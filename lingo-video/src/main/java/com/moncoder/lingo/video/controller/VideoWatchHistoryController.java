package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.dto.VideoWatchHistoryDTO;
import com.moncoder.lingo.video.domain.vo.VideoWatchHistoryVO;
import com.moncoder.lingo.video.service.IVmsVideoWatchHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户浏览视频历史记录表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-04-03 13:40:30
 */
@Api(tags = "视频观看历史管理")
@RestController
@RequestMapping("/video/history")
public class VideoWatchHistoryController {

    @Autowired
    private IVmsVideoWatchHistoryService videoWatchHistoryService;

    @ApiOperation("保存观看历史")
    @PostMapping("/save")
    public Result<String> save(@RequestBody @Valid VideoWatchHistoryDTO videoWatchHistoryDTO) {
        boolean flag = videoWatchHistoryService.save(videoWatchHistoryDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取用户全部观看历史")
    @GetMapping("/list")
    public Result<List<VideoWatchHistoryVO>> getList(@RequestParam @NotNull Integer userId,
                                                     @RequestParam(required = false) String titleKeyWord) {
        List<VideoWatchHistoryVO> watchHistoryVOS
                = videoWatchHistoryService.getListByUserId(userId, titleKeyWord);
        return Result.success(watchHistoryVOS);
    }

    @ApiOperation("清空观看历史")
    @DeleteMapping("/all")
    public Result<String> clear(@RequestParam @NotNull Integer userId) {
        boolean flag = videoWatchHistoryService.clear(userId);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("批量删除观看历史")
    @DeleteMapping("/s")
    public Result<String> deleteBatch(@RequestParam @NotNull Integer userId,
                                      @RequestParam @NotNull List<Integer> videoIds) {
        boolean flag = videoWatchHistoryService.deleteBatch(userId, videoIds);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取用户全部观看历史（分页）")
    @GetMapping("/page")
    public Result<LPage<VideoWatchHistoryVO>> getPage(@RequestParam @NotNull Integer userId,
                                                      @RequestParam(defaultValue = "1") Long pageNum,
                                                      @RequestParam(defaultValue = "5") Long pageSize,
                                                      @RequestParam(required = false) String titleKeyWord) {
        LPage<VideoWatchHistoryVO> browseHistoryVOS
                = videoWatchHistoryService.getPageByUserId(userId, pageNum, pageSize, titleKeyWord);
        return Result.success(browseHistoryVOS);
    }
}
