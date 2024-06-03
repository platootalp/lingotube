package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.vo.CategoryVO;
import com.moncoder.lingo.video.service.ICategoryService;
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
 * 视频分类表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Api(tags = "视频分类管理")
@RestController
@RequestMapping("/video/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("获取全部分类")
    @GetMapping("/all")
    public Result<List<CategoryVO>> getAllCategories() {
        List<CategoryVO> categoryVOS = categoryService.getAllCategories();
        return Result.success(categoryVOS);
    }

    @ApiOperation("根据id获取分类信息")
    @GetMapping("/{id}")
    public Result<CategoryVO> getCategoryById(@PathVariable @NotNull Integer id) {
        CategoryVO categoryVO = categoryService.getCategoryById(id);
        return Result.success(categoryVO);
    }

    @ApiOperation("根据id获取分类名称")
    @GetMapping("/name/{id}")
    public Result<String> getCategoryName(@PathVariable @NotNull Integer id) {
        String categoryName = categoryService.getCategoryName(id);
        return Result.success(categoryName);
    }

}
