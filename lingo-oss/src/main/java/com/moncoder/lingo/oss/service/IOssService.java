package com.moncoder.lingo.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * @description 阿里云文件服务接口
 * @date 2024/4/17 14:14
 */
public interface IOssService {
    /**
     * 文件上传
     * @param file 文件
     * @param prefix 文件存储前缀
     * @return
     */
    String upload(MultipartFile file, String prefix);

    /**
     *
     * @param fileList
     * @param prefix
     * @return
     */
    List<String> uploadBatch(List<MultipartFile> fileList, String prefix);

    void delete(String url);
}
