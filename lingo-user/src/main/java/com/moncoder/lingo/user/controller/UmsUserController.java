package com.moncoder.lingo.user.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@RestController
@RequestMapping("/umsUser")
@Api(tags = "用户管理")
public class UmsUserController {

    @Autowired
    private IUmsUserService userService;

    @GetMapping("/{phone}")
    public Result<String> sendCode(@PathVariable String phone){
        return Result.success(userService.sendCode(phone));
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO){
        return Result.success(userService.register(userRegisterDTO));
    }
}
