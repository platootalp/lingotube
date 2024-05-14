package com.moncoder.lingo.search.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.search.domain.EsVideo;
import com.moncoder.lingo.search.service.IEsVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO 搜索视频管理
 * @date 2024/5/13 11:19
 */
@Api(tags = "搜索视频管理")
@RestController
@RequestMapping("/search/video")
public class EsVideoController {

    @Autowired
    private IEsVideoService esVideoService;

    @ApiOperation("根据id导入视频到ES中")
    @PostMapping("/")
    public Result<EsVideo> create(@RequestParam Integer id) {
        EsVideo video = esVideoService.create(id);
        return Result.success(video);
    }

    @ApiOperation("导入所有视频到ES中")
    @PostMapping("/all")
    public Result<Integer> importAll() {
        int nums = esVideoService.importAll();
        return Result.success(nums);
    }

    @ApiOperation("删除ES中所有视频")
    @DeleteMapping("/all")
    public Result<String> deleteAll() {
        esVideoService.deleteAll();
        return Result.success();
    }

    @ApiOperation("删除ES中指定id视频")
    @DeleteMapping("/{id}")
    public Result<Integer> delete(@PathVariable Integer id) {
        esVideoService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("批量删除ES中视频")
    @DeleteMapping("/batch")
    public Result<Integer> deleteBatch(@RequestParam List<Integer> ids) {
        esVideoService.deleteBatch(ids);
        return Result.success();
    }

    @ApiOperation("搜索")
    @GetMapping("/search")
    public Result<List<EsVideo>> search(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "levels", required = false) List<String> levels,
            @RequestParam(value = "categories", required = false) List<String> categories,
            @RequestParam(value = "minDuration", defaultValue = "0") Integer minDuration,
            @RequestParam(value = "maxDuration", required = false) Integer maxDuration,
            @RequestParam(value = "sortBy", defaultValue = "1") Integer sortBy,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<EsVideo> result = esVideoService.search(key, levels, categories,
                minDuration, maxDuration, sortBy, pageNum, pageSize);
        return Result.success(result);
    }


}
