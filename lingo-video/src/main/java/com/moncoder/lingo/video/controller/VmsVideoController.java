package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.common.constant.VideoConstant;
import com.moncoder.lingo.video.domain.vo.VideoPlayVO;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;
import com.moncoder.lingo.video.service.IVmsVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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

    @ApiOperation("根据id获取视频播放信息")
    @GetMapping("/{id}")
    public Result<VideoPlayVO> getVideo(@PathVariable("id") @NotNull Integer id) {
        VideoPlayVO videoPlayVO = videoService.getVideo(id);
        return Result.success(videoPlayVO);
    }

    @ApiOperation("获取视频点赞数")
    @GetMapping("/likes/{id}")
    public Result<Integer> getVideoLikes(@PathVariable @NotNull Integer id) {
        return Result.success(videoService.getVideoLikes(id));
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

    @ApiOperation("获取当前视频相关视频")
    @GetMapping("/related/s")
    public Result<List<VideoViewVO>> getRelatedVideoList(@RequestParam @NotNull Integer id,
                                                         @RequestParam @NotBlank String levelName) {
        List<VideoViewVO> videos = videoService.getRelatedVideos(id, levelName,
                VideoConstant.VMS_RELATED_VIDEO_COUNT);
        return Result.success(videos);
    }

    @ApiOperation("获取分类id下分页视频")
    @GetMapping("/category/page")
    public Result<LPage<VideoViewVO>> getPageByCategoryId(
            @RequestParam("categoryId") @NotNull Integer categoryId,
            @RequestParam(value = "curPage", defaultValue = "1") Long curPage,
            @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
            @ApiParam(value = "排序方式，可选值：1-按上传时间（降序），2-按上传时间（降序），" +
                    "3-播放量（降序）", defaultValue = "1")
            @RequestParam(value = "sort", defaultValue = "1") Integer sort) {
        LPage<VideoViewVO> videoViewVOLPage = videoService.getPageByCategoryId(categoryId, curPage, pageSize, sort);
        return Result.success(videoViewVOLPage);
    }

    @ApiOperation("获取等级id下分页视频")
    @GetMapping("/level/page")
    public Result<LPage<VideoViewVO>> getPageByLevelId(
            @RequestParam("levelId") @NotNull Integer levelId,
            @RequestParam(value = "curPage", defaultValue = "1") Long curPage,
            @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
            @ApiParam(value = "排序方式，可选值：1-按上传时间（降序），2-按上传时间（降序），" +
                    "3-播放量（降序）", defaultValue = "1")
            @RequestParam(value = "sort", defaultValue = "1") Integer sort) {
        LPage<VideoViewVO> videoViewVOLPage = videoService.getPageByLevelId(levelId, curPage, pageSize, sort);
        return Result.success(videoViewVOLPage);
    }

    @ApiOperation("增加视频播放量")
    @PutMapping("/views/add")
    public Result<String> addVideoViews(@RequestParam @NotNull Integer id,
                                        @RequestParam(value = "num", defaultValue = "1") Integer num) {
        boolean flag = videoService.addVideoViews(id, num);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }
}
