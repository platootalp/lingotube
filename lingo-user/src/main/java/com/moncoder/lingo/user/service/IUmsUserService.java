package com.moncoder.lingo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.user.domain.dto.UserLoginDTO;
import com.moncoder.lingo.user.domain.dto.UserPasswordUpdateDTO;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.domain.dto.UserInfoUpdateDTO;
import com.moncoder.lingo.user.domain.vo.UserCommentInfoVO;
import com.moncoder.lingo.user.domain.vo.UserInfoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
public interface IUmsUserService extends IService<UmsUser> {

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
    UserCommentInfoVO getCommentInfo();

}
