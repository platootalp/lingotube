package com.moncoder.lingo.user.client;

import com.moncoder.lingo.common.api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Moncoder
 * @version 1.0
 * @description 阿里云文件服务rpc调用
 * @date 2024/4/17 15:25
 */
@FeignClient("lingo-oss")
public interface OssClient {

    /**
     * 上传用户头像
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/oss/upload/user/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<String> uploadUserAvatar(@RequestPart("file") MultipartFile file);

    /**
     * 上传用户头像
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/oss/upload/qrcode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<String> uploadQRCode(@RequestPart("file") MultipartFile file);
}
