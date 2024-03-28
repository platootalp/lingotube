package com.moncoder.lingo.user.service.impl;

import com.moncoder.lingo.common.constant.UserConstant;
import com.moncoder.lingo.common.exception.FileUploadException;
import com.moncoder.lingo.common.exception.IllegalArgumentException;
import com.moncoder.lingo.common.util.FileUtil;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.user.service.IUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Moncoder
 * @version 1.0
 * @description 文件上传实现类
 * @date 2024/3/28 10:50
 */
@Service
public class UploadServiceImpl implements IUploadService {

    @Override
    public String upload(MultipartFile file, String dirName) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为null！");
        }
        // 将文件保存到服务器并返回url
        String uri = null;
        try {
            uri = FileUtil.saveFile(file, dirName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException("文件上传失败！");
        }
        return uri;
    }
}
