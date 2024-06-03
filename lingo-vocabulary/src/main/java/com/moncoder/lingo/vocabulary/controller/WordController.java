package com.moncoder.lingo.vocabulary.controller;

import com.moncoder.lingo.common.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 单词表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 11:45:58
 */
@Api(tags = "单词管理")
@RestController
@RequestMapping("/word")
public class WordController {

    @ApiOperation("导入单词")
    @PostMapping("/")
    public Result<String> save(){
        return Result.failed();
    }
}
