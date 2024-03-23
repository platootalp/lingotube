package com.moncoder.lingo.user.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户信息
 * @date 2024/3/23 16:56
 */
@Setter
@Getter
@ApiModel("用户信息")
public class UserInfoVO {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像，存储url")
    private String avatar;

    @ApiModelProperty("个人介绍")
    private String introduce;

    @ApiModelProperty("性别：0->未知；1->男；2->女")
    private Byte gender;

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("地址")
    private String address;

}
