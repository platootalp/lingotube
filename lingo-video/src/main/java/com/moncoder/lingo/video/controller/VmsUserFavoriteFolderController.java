package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Page;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.dto.UserFavoriteFolderDTO;
import com.moncoder.lingo.video.domain.dto.UserFavoriteFolderUpdateDTO;
import com.moncoder.lingo.video.domain.vo.UserFavoriteFolderVO;
import com.moncoder.lingo.video.domain.vo.UserFavoriteVideoVo;
import com.moncoder.lingo.video.service.IVmsUserFavoriteFolderService;
import com.moncoder.lingo.video.service.IVmsUserFavoriteFolderVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户收藏夹表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Api(tags = "用户收藏夹管理")
@RestController
@RequestMapping("/video/fav")
public class VmsUserFavoriteFolderController {

    @Autowired
    private IVmsUserFavoriteFolderService favoriteFolderService;
    @Autowired
    private IVmsUserFavoriteFolderVideoService folderVideoService;

    // TODO 请求体里面加上文件字段
    @ApiOperation("创建收藏夹")
    @PostMapping("/folder")
    public Result<String> create(@RequestBody @Valid UserFavoriteFolderDTO userFavoriteFolderDTO) {
        boolean flag = favoriteFolderService.create(userFavoriteFolderDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }


    @ApiOperation("删除非默认收藏夹")
    @DeleteMapping("/folder/{id}")
    public Result<String> delete(@PathVariable @NotNull Integer id) {
        boolean flag = favoriteFolderService.delete(id);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    // TODO 请求体里面加上文件字段
    @ApiOperation("修改收藏夹")
    @PutMapping("/folder/{id}")
    public Result<String> update(@PathVariable @NotNull Integer id,
                                 @RequestBody @Valid UserFavoriteFolderUpdateDTO userFavoriteFolderUpdateDTO) {
        boolean flag = favoriteFolderService.update(id, userFavoriteFolderUpdateDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取指定用户收藏夹列表")
    @GetMapping("/folder/list/{userId}")
    public Result<List<UserFavoriteFolderVO>> list(@PathVariable("userId") @NotNull Integer userId) {
        List<UserFavoriteFolderVO> list = favoriteFolderService.getList(userId);
        if (list == null) {
            return Result.failed();
        }
        return Result.success(list);
    }


    @ApiOperation("分页查询收藏夹下的所有视频")
    @GetMapping("/folder/{folder_id}")
    public Result<List<Page<UserFavoriteVideoVo>>> getPage(@PathVariable @NotNull Integer folderId) {
        return Result.failed();
    }

    @ApiOperation("批量删除收藏视频")
    @DeleteMapping("/video/s")
    public Result<String> deleteBatch(@RequestParam @NotNull List<Integer> ids) {
        boolean flag = folderVideoService.removeBatchByIds(ids);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("复制当前收藏夹视频到其他收藏夹")
    @PostMapping("/video/copy/s")
    public Result<String> copyBatchVideoToOtherFolder(@RequestParam @NotNull List<Integer> ids,
                                                      @RequestParam @NotNull Integer curFolderId,
                                                      @RequestParam @NotNull List<Integer> newFolderIds) {
        boolean flag = folderVideoService.copyBatchVideoToOtherFolders(ids, curFolderId, newFolderIds);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("移动当前收藏夹视频到其他收藏夹")
    @PostMapping("/video/move")
    public Result<String> copyBatchVideoToOtherFolder(@RequestParam @NotNull List<Integer> ids,
                                                      @RequestParam @NotNull Integer curFolderId,
                                                      @RequestParam @NotNull Integer newFolderId) {
        boolean flag = folderVideoService.moveBatchVideoToOtherFolder(ids, curFolderId, newFolderId);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }
}
