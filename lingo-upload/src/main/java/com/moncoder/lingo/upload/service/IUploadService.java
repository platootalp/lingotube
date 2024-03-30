package com.moncoder.lingo.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author lenovo
 * @version 1.0
 * @description 文件上传接口
 * @date 2024/3/28 10:49
 */
public interface IUploadService {

    /**
     * 上传文件
     * @param file
     * @param dirName 目录名
     * @return 文件保存uri
     */
    String upload(MultipartFile file, String dirName);
}
