package com.moncoder.lingo.video.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.util.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Moncoder
 * @version 1.0
 * @description 文件存储控制器
 * @date 2024/4/15 21:24
 */
@Api(tags = "文件存储控制器")
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssUtil ossUtil;

    @PostMapping("/upload")
    public Result<String> saveOss(@RequestParam MultipartFile file) {
        try {
            // 获取原始的文件名
            String originalFilename = file.getOriginalFilename();
            // 在oss中存储名字就是UUID + 文件的后缀名
            assert originalFilename != null;
            String formattedDateTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            String objectName = formattedDateTime +
                    originalFilename.substring(originalFilename.lastIndexOf("."));
            String resultUrl = ossUtil.upload(file.getBytes(), objectName);
            return Result.success(resultUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
