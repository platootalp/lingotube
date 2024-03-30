package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Page;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.vo.UserFavoriteVideoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户收藏视频记录表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Api(tags = "用户收藏夹视频管理")
@RestController
@RequestMapping("/user/fav/video")
public class VmsUserFavoriteVideoController {

    @ApiOperation("分页查询收藏夹下的视频")
    @GetMapping("/{folder_id}")
    public Result<List<Page<UserFavoriteVideoVo>>> getPage(@PathVariable @NotNull Integer folderId){
        return Result.failed();
    }

}
