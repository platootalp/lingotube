package com.moncoder.lingo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.domain.dto.UserUpdateInfoDTO;
import com.moncoder.lingo.user.domain.vo.UserInfoVO;
import org.springframework.core.io.InputStreamResource;
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
     * @param phone
     * @return
     */
    String sendCode(String phone);

    /**
     * 注册
     *
     * @param userRegisterDTO
     * @return
     */
    Boolean register(UserRegisterDTO userRegisterDTO);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    UserInfoVO getInfo(Integer id);

    /**
     * 修改用户信息
     *
     * @param userUpdateInfoDTO
     * @return
     */
    Boolean updateInfo(UserUpdateInfoDTO userUpdateInfoDTO);

    /**
     * 修改密码前对验证码进行验证
     *
     * @param phone
     * @param code
     * @return
     */
    Boolean verifyCode(String phone, String code);

    /**
     * 修改用户密码
     *
     * @param phone
     * @param password
     * @return
     */
    Boolean updatePassword(String phone, String password);

    /**
     * 修改用户头像
     * @param id
     * @param file
     * @return
     */
    Boolean uploadAvatar(Integer id, MultipartFile file);

    /**
     * 获取用户头像
     * @param id
     */
    String getAvatar(Integer id);
}
