package com.moncoder.lingo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.user.domain.dto.UserLoginDTO;
import com.moncoder.lingo.user.domain.dto.UserPasswordUpdateDTO;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.domain.dto.UserInfoUpdateDTO;
import com.moncoder.lingo.user.domain.vo.UserShowInfoVO;
import com.moncoder.lingo.user.domain.vo.UserInfoVO;
import com.moncoder.lingo.user.domain.vo.WeChatAccessVO;
import com.moncoder.lingo.user.domain.vo.WeChatUserInfoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
public interface IUserService extends IService<UmsUser> {

    /**
     * @param email
     * @return
     */
    boolean sendVerifyCode(String email);

    /**
     * 注册
     *
     * @param userRegisterDTO
     * @return
     */
    boolean register(UserRegisterDTO userRegisterDTO);

    /**
     * 登陆
     *
     * @param userLoginDTO
     * @return
     */
    String login(UserLoginDTO userLoginDTO);

    /**
     * 获取当前用户信息
     *
     * @param
     * @return
     */
    UserInfoVO getInfo();

    /**
     * 修改当前用户信息
     *
     * @param userUpdateInfoDTO
     * @return
     */
    boolean updateInfo(UserInfoUpdateDTO userUpdateInfoDTO);

    /**
     * 对验证码进行验证
     *
     * @param phone
     * @param code
     * @return
     */
    boolean verifyCode(String phone, String code);

    /**
     * 修改当前用户密码
     *
     * @param passwordUpdateDTO
     * @return
     */
    boolean updatePassword(UserPasswordUpdateDTO passwordUpdateDTO);

    /**
     * 修改当前用户头像
     *
     * @param file
     * @return
     */
    boolean updateAvatar(MultipartFile file);

    /**
     * 获取当前用户头像
     */
    String getAvatar();

    /**
     * 获取用户评论时候需要的信息（昵称和头像）
     *
     * @return
     */
    UserShowInfoVO getShowInfo(Integer id);

    /**
     * wx签名验证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    String wxSignatureCheck(String signature, String timestamp, String nonce, String echostr);

    /**
     * 微信登录
     * @return
     */
    String getWeChatLoginQRCode() throws UnsupportedEncodingException;


    /**
     * 微信回调函数
     * @param code
     * @return
     */
    WeChatAccessVO weChatCallback(String code);

    /**
     * 获取微信登录用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    WeChatUserInfoVO getWeChatLoginUserInfo(String accessToken, String openId);

    /**
     * 生成二维码
     * @return
     */
    Map<String, String> generateQRCode() throws IOException;

    void scanLogin(String qrCodeKey);

    String checkUserLoginStatus(String qrCodeKey);
}
