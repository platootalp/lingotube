package com.moncoder.lingo.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户信息修改参数
 * @date 2024/3/26 10:05
 */
@Getter
@Setter
@ApiModel("用户信息修改参数")
public class UserInfoUpdateDTO {
    @NotNull
    @ApiModelProperty(value = "id",required = true)
    private Integer id;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("个人介绍")
    private String introduce;
    @ApiModelProperty("性别：0->未知；1->男；2->女")
    private Byte gender;
    @ApiModelProperty("生日")
    private LocalDate birthday;
    @ApiModelProperty("地址")
    private String address;
}
