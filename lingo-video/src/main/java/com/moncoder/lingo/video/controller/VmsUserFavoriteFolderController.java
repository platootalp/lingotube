package com.moncoder.lingo.video.controller;


import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.dto.FavoriteFolderCreateDTO;
import com.moncoder.lingo.video.domain.dto.FavoriteFolderUpdateDTO;
import com.moncoder.lingo.video.domain.vo.FavoriteFolderVO;
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
    public Result<String> create(@RequestBody @Valid FavoriteFolderCreateDTO favoriteFolderCreateDTO) {
        boolean flag = favoriteFolderService.create(favoriteFolderCreateDTO);
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
                                 @RequestBody @Valid FavoriteFolderUpdateDTO favoriteFolderUpdateDTO) {
        boolean flag = favoriteFolderService.update(id, favoriteFolderUpdateDTO);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取指定用户收藏夹列表")
    @GetMapping("/list/{userId}")
    public Result<List<FavoriteFolderVO>> list(@PathVariable("userId") @NotNull Integer userId) {
        List<FavoriteFolderVO> list = favoriteFolderService.getList(userId);
        if (list == null) {
            return Result.failed();
        }
        return Result.success(list);
    }

}
