package com.moncoder.lingo.user.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.domain.dto.UserUpdateInfoDTO;
import com.moncoder.lingo.user.domain.vo.UserInfoVO;
import com.moncoder.lingo.user.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    public Result<String> sendCode(@PathVariable @NotEmpty String phone){
        String code = userService.sendCode(phone);
        return Result.success(code);
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

    @ApiOperation("获取当前用户信息")
    @GetMapping("/info/{id}")
    public Result<UserInfoVO> getUserInfo(@PathVariable @NotNull Integer id){
        UserInfoVO userInfoVo = userService.getInfo(id);
        return Result.success(userInfoVo);
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/info")
    public Result<String> updateUserInfo(@RequestBody @Valid UserUpdateInfoDTO userUpdateInfoDTO){
        Boolean flag = userService.updateInfo(userUpdateInfoDTO);
        if(!flag){
            return Result.failed("更新失败！");
        }
        return Result.success("更新成功！");
    }
}
