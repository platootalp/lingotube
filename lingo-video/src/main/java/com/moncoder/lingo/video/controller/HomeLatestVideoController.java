package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.vo.VideoViewVO;
import com.moncoder.lingo.video.service.IVmsHomeLatestVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 最新视频表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 13:18:19
 */
@Api(tags="最新视频管理")
@RestController
@RequestMapping("/home/video/latest")
public class HomeLatestVideoController {

    @Autowired
    private IVmsHomeLatestVideoService latestVideoService;

    @ApiOperation("获取首页推荐视频")
    @GetMapping("/list")
    public Result<List<VideoViewVO>> getLatestVideos(){
        List<VideoViewVO> videoViewVOS = latestVideoService.getLatestVideos();
        return Result.success(videoViewVOS);
    }
}
