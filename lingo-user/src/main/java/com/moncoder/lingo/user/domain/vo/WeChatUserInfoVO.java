package com.moncoder.lingo.user.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description 微信用户信息返回值
 * @date 2024/7/10 21:37
 */
@Setter
@Getter
@ApiModel("微信用户信息返回值")
public class WeChatUserInfoVO {
    @ApiModelProperty("用户的唯一标识")
    private String openid;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private Integer sex;
    @ApiModelProperty("用户个人资料填写的省份")
    private String province;
    @ApiModelProperty("普通用户个人资料填写的城市")
    private String city;
    @ApiModelProperty("国家，如中国为CN")
    private String country;
    @ApiModelProperty("用户头像")
    private String headimgurl;
    @ApiModelProperty("用户特权信息，json数组，如微信沃卡用户为（chinaunicom）")
    private List<String> privilege;
    @ApiModelProperty("用户统一标识。针对一个微信开放平台账号下的应用，同一用户的unionid是唯一的")
    private String unionid;
}
