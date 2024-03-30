package com.moncoder.lingo.upload.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.common.constant.SystemConstant;
import com.moncoder.lingo.upload.service.IUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author Moncoder
 * @version 1.0
 * @description 文件上传控制器
 * @date 2024/3/28 10:47
 */
@Api(tags = "文件上传管理")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @ApiOperation("上传文件")
    @PostMapping("")
    public String upload(@RequestPart("file") @NotNull MultipartFile file) {
        return uploadService.upload(file, SystemConstant.LINGO_DEFAULT_FILE_PATH);
    }
}
