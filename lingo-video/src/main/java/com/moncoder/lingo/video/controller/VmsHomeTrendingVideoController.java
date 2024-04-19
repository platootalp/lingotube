package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;
import com.moncoder.lingo.video.service.IVmsHomeTrendingVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 最热视频表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 13:18:20
 */
@Api(tags = "最热视频管理")
@RestController
@RequestMapping("/home/video/trending")
public class VmsHomeTrendingVideoController {

    @Autowired
    private IVmsHomeTrendingVideoService trendingVideoService;

    @ApiOperation("获取首页推荐视频")
    @GetMapping("/list")
    public Result<List<VideoViewVO>> getTrendingVideos(){
        List<VideoViewVO> videoViewVOS = trendingVideoService.getTrendingVideos();
        return Result.success(videoViewVOS);
    }

}
