package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.vo.CategoryVO;
import com.moncoder.lingo.video.domain.vo.LevelVO;
import com.moncoder.lingo.video.service.IVmsLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 视频等级表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-04-26 11:40:11
 */
@Api(tags = "视频等级管理")
@RestController
@RequestMapping("/video/level")
public class LevelController {

    @Autowired
    private IVmsLevelService levelService;

    @ApiOperation("获取全部等级")
    @GetMapping("/all")
    public Result<List<LevelVO>> getAllLevel() {
        List<LevelVO> levelVOS = levelService.getAllLevel();
        return Result.success(levelVOS);
    }

    @ApiOperation("根据id获取等级信息")
    @GetMapping("/{id}")
    public Result<LevelVO> getLevelById(@PathVariable @NotNull Integer id) {
        LevelVO levelVO = levelService.getLevelById(id);
        return Result.success(levelVO);
    }

    @ApiOperation("根据id获取等级名称")
    @GetMapping("/name/{id}")
    public Result<String> getLevelName(@PathVariable @NotNull Integer id) {
        String levelName = levelService.getLevelName(id);
        return Result.success(levelName);
    }
}
