package com.moncoder.lingo.user.controller;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.user.domain.dto.UserLoginDTO;
import com.moncoder.lingo.user.domain.dto.UserPasswordUpdateDTO;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.domain.dto.UserInfoUpdateDTO;
import com.moncoder.lingo.user.domain.vo.UserShowInfoVO;
import com.moncoder.lingo.user.domain.vo.UserInfoVO;
import com.moncoder.lingo.user.domain.vo.WeChatAccessVO;
import com.moncoder.lingo.user.domain.vo.WeChatUserInfoVO;
import com.moncoder.lingo.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
public class UserController {

    @Autowired
    private IUserService userService;

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
    @GetMapping("/info")
    public Result<UserInfoVO> getUserInfo() {
        return Result.success(userService.getInfo());
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/info")
    public Result<String> updateInfo(@RequestBody @Valid UserInfoUpdateDTO userUpdateInfoDTO) {
        boolean flag = userService.updateInfo(userUpdateInfoDTO);
        if (!flag) {
            return Result.failed("更新失败！");
        }
        return Result.success("更新成功！", null);
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/reset/password")
    public Result<String> updatePassword(@RequestBody UserPasswordUpdateDTO passwordUpdateDTO) {
        boolean flag = userService.updatePassword(passwordUpdateDTO);
        if (!flag) {
            return Result.failed("更新失败！");
        }
        return Result.success("更新成功！", null);
    }

    @ApiOperation("修改用户头像")
    @PutMapping("/avatar")
    public Result<String> updateAvatar(@RequestPart("file") @NotNull MultipartFile file) {
        boolean flag = userService.updateAvatar(file);
        if (!flag) {
            return Result.failed();
        }
        return Result.success();
    }

    @ApiOperation("获取用户头像")
    @GetMapping("/avatar")
    public Result<String> getAvatar() {
        return Result.success(userService.getAvatar());
    }

    @ApiOperation("根据id获取用户展示信息")
    @GetMapping("/show/info/{id}")
    public Result<UserShowInfoVO> getUserShowInfo(@PathVariable("id")@NotNull Integer id) {
        return Result.success(userService.getShowInfo(id));
    }

    @ApiOperation("生成二维码")
    @GetMapping("/qrcode")
    public Result<String> generateQRCode() throws IOException {
        return Result.success(userService.generateQRCode());
    }

    @ApiOperation("微信签名验证")
    @GetMapping("/wx/check")
    public String weChatSignatureCheck(@RequestParam("signature") String signature,
                                       @RequestParam("timestamp") String timestamp,
                                       @RequestParam("nonce") String nonce,
                                       @RequestParam("echostr")String echostr) {
        return userService.wxSignatureCheck(signature,timestamp,nonce,echostr);
    }

    @ApiOperation("获取微信登陆二维码")
    @GetMapping("/wx/qrcode")
    public Result<String> getWeChatLoginQRCode() throws UnsupportedEncodingException {
        return Result.success(userService.getWeChatLoginQRCode());
    }

    @ApiOperation("微信回调接口")
    @GetMapping("/wx/callback")
    public Result<WeChatAccessVO> weChatCallback(@RequestParam("code") String code) {
        return Result.success(userService.weChatCallback(code));
    }

    @ApiOperation("获取微信登陆用户信息")
    @GetMapping("/wx/userInfo")
    public Result<WeChatUserInfoVO> getWeChatLoginUserInfo(@RequestParam("accessToken")String accessToken,
                                                           @RequestParam("openId")String openId){
        return Result.success(userService.getWeChatLoginUserInfo(accessToken,openId));
    }
}

