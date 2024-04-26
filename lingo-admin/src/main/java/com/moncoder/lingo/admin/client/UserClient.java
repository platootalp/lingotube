package com.moncoder.lingo.admin.client;

import com.moncoder.lingo.admin.domain.vo.UserShowInfoVO;
import com.moncoder.lingo.common.api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 * @version 1.0
 * @description 用户服务rpc调用
 * @date 2024/4/17 17:06
 */
@FeignClient("lingo-user")
public interface UserClient {

    /**
     * 获取指定用户展示信息
     *
     * @return
     */
    @GetMapping("/user/show/info/{id}")
    Result<UserShowInfoVO> getUserShowInfo(@PathVariable("id") @NotNull Integer id);
}
