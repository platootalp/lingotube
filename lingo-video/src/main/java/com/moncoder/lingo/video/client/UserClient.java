package com.moncoder.lingo.video.client;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.video.domain.vo.UserCommentInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lenovo
 * @version 1.0
 * @description 用户服务rpc调用
 * @date 2024/4/17 17:06
 */
@FeignClient("lingo-user")
public interface UserClient {

    /**
     * 获取用户评论信息
     * @return
     */
    @GetMapping("/user/comment/info")
    Result<UserCommentInfoVO> getUserCommentInfo();
}
