package com.moncoder.lingo.user.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 * @version 1.0
 * @description rpc调用
 * @date 2024/3/28 15:57
 */
@FeignClient("lingo-upload")
public interface UploadClient {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart("file") @NotNull MultipartFile file);
}
