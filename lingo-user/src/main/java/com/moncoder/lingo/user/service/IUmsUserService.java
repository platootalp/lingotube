package com.moncoder.lingo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
public interface IUmsUserService extends IService<UmsUser> {

    /**
     *
     * @param phone
     * @return
     */
    String sendCode(String phone);

    /**
     * 注册
     * @param userRegisterDTO
     * @return
     */
    String register(UserRegisterDTO userRegisterDTO);

}
