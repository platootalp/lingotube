package com.moncoder.lingo.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户密码修改参数
 * @date 2024/4/8 20:42
 */
@Getter
@Setter
@ApiModel("用户密码修改参数")
public class UserPasswordUpdateDTO {

    @Email
    @NotBlank
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
    @NotBlank
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
    @NotBlank
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
