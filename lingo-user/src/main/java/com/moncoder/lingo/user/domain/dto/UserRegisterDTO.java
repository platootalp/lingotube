package com.moncoder.lingo.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户注册参数
 * @date 2024/3/20 16:02
 */
@Setter
@Getter
@ApiModel("用户注册参数")
public class UserRegisterDTO {


    @NotNull
    @ApiModelProperty("手机号")
    private String phone;
    @NotNull
    @ApiModelProperty("验证码")
    private String code;
    @NotNull
    @ApiModelProperty("用户名")
    private String username;
    @NotNull
    @ApiModelProperty("密码（加密存储）")
    private String password;

}
