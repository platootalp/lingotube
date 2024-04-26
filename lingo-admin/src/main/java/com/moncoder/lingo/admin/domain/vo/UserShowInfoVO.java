package com.moncoder.lingo.admin.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Moncoder
 * @version 1.0
 * @description 用户评论信息
 * @date 2024/4/5 14:57
 */
@Setter
@Getter
@ApiModel("用户评论信息")
public class UserShowInfoVO {
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像，存储url")
    private String avatar;
}
