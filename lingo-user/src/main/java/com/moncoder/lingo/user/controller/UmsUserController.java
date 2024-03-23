package com.moncoder.lingo.user.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiOperation("发送验证码")
    @GetMapping("/{phone}")
    public Result<String> sendCode(@PathVariable String phone){
        String data = userService.sendCode(phone);
        return Result.success(data);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        Boolean flag = userService.register(userRegisterDTO);
        if(!flag){
            return Result.failed("注册失败！");
        }
        return Result.success("注册成功！");
    }

}
