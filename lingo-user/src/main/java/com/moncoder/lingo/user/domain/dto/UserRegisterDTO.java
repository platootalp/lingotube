package com.moncoder.lingo.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO 用户注册参数
 * @date 2024/3/20 16:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    private String username;
    private String password;
    private String phone;
}
