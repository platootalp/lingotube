package com.moncoder.lingo.video.controller;


import com.moncoder.lingo.common.api.Page;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.dto.FolderVideoCopyDTO;
import com.moncoder.lingo.video.domain.dto.FolderVideoMoveDTO;
import com.moncoder.lingo.video.domain.vo.FavoriteVideoVo;
import com.moncoder.lingo.video.service.IVmsUserFavoriteFolderVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户收藏视频记录表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-04-01 13:42:53
 */
@Api(tags = "用户收藏视频管理")
@RestController
@RequestMapping("/video/fav/video")
public class VmsUserFavoriteFolderVideoController {

    @Autowired
    private IVmsUserFavoriteFolderVideoService folderVideoService;

    @ApiOperation("分页查询收藏夹下的所有视频")
    @GetMapping("/{folder_id}")
    public Result<List<Page<FavoriteVideoVo>>> getPage(@PathVariable @NotNull Integer folderId) {
        return Result.failed();
    }

    @ApiOperation("批量删除收藏视频")
    @DeleteMapping("/s")
    public Result<String> deleteBatch(@RequestParam @NotNull List<Integer> ids) {
        boolean flag = folderVideoService.removeBatchByIds(ids);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("复制当前收藏夹视频到其他收藏夹")
    @PostMapping("/copy/s")
    public Result<String> copyVideosToFolders(@RequestBody @Valid FolderVideoCopyDTO folderVideoCopyDTO) {
        boolean flag = folderVideoService.copyVideosToFolders(folderVideoCopyDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("移动当前收藏夹视频到其他收藏夹")
    @PostMapping("/move")
    public Result<String> moveVideosToFolder(@RequestBody @Valid FolderVideoMoveDTO folderVideoMoveDTO) {
        boolean flag = folderVideoService.moveVideosToFolder(folderVideoMoveDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }
}
