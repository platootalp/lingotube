package com.moncoder.lingo.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户登陆参数
 * @date 2024/4/10 15:51
 */
@Getter
@Setter
@ApiModel("用户登陆参数")
public class UserLoginDTO {
    @NotBlank
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
    @NotBlank
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
