package com.moncoder.lingo.user.controller;

import com.moncoder.lingo.api.domain.UserCommentInfoVO;
import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.user.domain.dto.UserLoginDTO;
import com.moncoder.lingo.user.domain.dto.UserPasswordUpdateDTO;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.domain.dto.UserInfoUpdateDTO;
import com.moncoder.lingo.user.domain.vo.UserInfoVO;
import com.moncoder.lingo.user.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UmsUserController {

    @Autowired
    private IUmsUserService userService;

    @ApiOperation("发送验证码")
    @PostMapping("/verifyCode")
    public Result<String> sendVerifyCode(@RequestParam @NotBlank String email) {
        boolean flag = userService.sendVerifyCode(email);
        if (!flag) {
            return Result.failed("发送失败！");
        }
        return Result.success("发送成功！", null);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        boolean flag = userService.register(userRegisterDTO);
        if (!flag) {
            return Result.failed("注册失败！");
        }
        return Result.success("注册成功！", null);
    }
    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO);
        return Result.success("登陆成功！", token);
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping("/info/{id}")
    public Result<UserInfoVO> getUserInfo(@PathVariable @NotNull Integer id) {
        UserInfoVO userInfoVo = userService.getInfo(id);
        return Result.success(userInfoVo);
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/info")
    public Result<String> updateInfo(@RequestBody @Valid UserInfoUpdateDTO userUpdateInfoDTO) {
        Boolean flag = userService.updateInfo(userUpdateInfoDTO);
        if (!flag) {
            return Result.failed("更新失败！");
        }
        return Result.success("更新成功！", null);
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/password/reset")
    public Result<String> updatePassword(@RequestBody UserPasswordUpdateDTO passwordUpdateDTO) {
        Boolean flag = userService.updatePassword(passwordUpdateDTO);
        if (!flag) {
            return Result.failed("更新失败！");
        }
        return Result.success("更新成功！", null);
    }

    @ApiOperation("修改用户头像")
    @PutMapping("/avatar")
    public Result<String> updateAvatar(@RequestParam("id") @NotNull Integer id,
                                       @RequestPart("avatar") @NotNull MultipartFile file) {
        Boolean flag = userService.updateAvatar(id, file);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取用户头像")
    @GetMapping("/avatar")
    public Result<String> getAvatar(@RequestParam("id") @NotNull Integer id) {
        String avatar = userService.getAvatar(id);
        if (avatar == null) {
            return Result.failed();
        }
        return Result.success(avatar);
    }

    /** Fegin 客户端 **/
    @ApiOperation("获取用户评论时所需信息")
    @GetMapping("/comment/info/{id}")
    public UserCommentInfoVO getUserCommentInfo(@PathVariable @NotNull Integer id) {
        return userService.getCommentInfo(id);
    }

}
