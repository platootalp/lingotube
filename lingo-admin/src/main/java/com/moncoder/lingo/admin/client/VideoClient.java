package com.moncoder.lingo.admin.client;

import com.moncoder.lingo.common.api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2024/4/29 15:27
 */
@FeignClient("lingo-video")
public interface VideoClient {
    /**
     * 根据id获取分类名称
     * @param id
     * @return
     */
    @GetMapping("/video/category/name/{id}")
    Result<String> getCategoryName(@PathVariable("id") @NotNull Integer id);


    /**
     * 根据id获取等级名称
     * @param id
     * @return
     */
    @GetMapping("/video/level/name/{id}")
    Result<String> getLevelName(@PathVariable("id") @NotNull Integer id);
}
