package com.moncoder.lingo.user.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Moncoder
 * @version 1.0
 * @description 微信token返回值
 * @date 2024/7/10 21:29
 */
@Setter
@Getter
@ApiModel("微信token返回值")
public class WeChatAccessVO {
    @ApiModelProperty("接口调用凭证")
    private String accessToken;
    @ApiModelProperty("access_token接口调用凭证超时时间，单位（秒）")
    private Integer expiresIn;
    @ApiModelProperty("用户刷新access_token")
    private String refreshToken;
    @ApiModelProperty("授权用户唯一标识")
    private String openid;
    @ApiModelProperty("用户授权的作用域，使用逗号（,）分隔")
    private String scope;
    @ApiModelProperty("是否为快照用户")
    private Integer isSnapshotuser;
    @ApiModelProperty("用户统一标识。针对一个微信开放平台账号下的应用，同一用户的unionid是唯一的")
    private String unionid;
}
