package com.moncoder.lingo.video.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 * @version 1.0
 * @description rpc调用客户端
 * @date 2024/3/28 15:57
 */
@FeignClient("lingo-upload")
public interface UploadClient {

    @PostMapping("/upload")
    String upload(@RequestParam("file") @NotNull MultipartFile file);
}
