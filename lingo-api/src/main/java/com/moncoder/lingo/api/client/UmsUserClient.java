package com.moncoder.lingo.api.client;


import com.moncoder.lingo.api.domain.UserCommentInfoVO;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lenovo
 * @version 1.0
 * @description 用户服务rpc调用
 * @date 2024/4/5 14:30
 */
@FeignClient(value = "lingo-user", url = "http://127.0.0.1:8081/user/")
public interface UmsUserClient {

    @GetMapping("/comment/info/{id}")
    UserCommentInfoVO getUserCommentInfo(@PathVariable Integer id);
}
