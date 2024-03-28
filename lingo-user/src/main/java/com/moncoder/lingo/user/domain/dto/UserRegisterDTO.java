package com.moncoder.lingo.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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


    @NotEmpty
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
    @NotEmpty
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
    @NotBlank
    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;
    @NotEmpty
    @ApiModelProperty(value = "密码（加密存储）", required = true)
    private String password;

}
