package com.moncoder.lingo.oss.service.impl;

import cn.hutool.core.util.StrUtil;
import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.oss.service.IOssService;
import com.moncoder.lingo.oss.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Moncoder
 * @version 1.0
 * @description 阿里云文件服务
 * @date 2024/4/17 14:15
 */
@Service
public class OssServiceImpl implements IOssService {

    @Autowired
    private OssUtil ossUtil;

    @Override
    public String upload(MultipartFile file, String prefix) {
        // 1.获取原始的文件名
        if (file == null || StrUtil.isBlank(prefix)) {
            throw new IllegalArgumentException("参数不正确！");
        }
        // 2.在oss中存储名 前缀+时间戳+后缀名
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)) {
            throw new IllegalArgumentException("文件名不能为空白！");
        }
        String formattedDateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String objectName = prefix + formattedDateTime +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        // 3.上传文件，返回存储路径
        String url = "";
        try {
            url = ossUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            throw new ApiException(e);
        }
        return url;
    }

    @Override
    public List<String> uploadBatch(List<MultipartFile> fileList, String prefix) {
        return fileList.stream().map(file -> upload(file, prefix)).collect(Collectors.toList());
    }
}
