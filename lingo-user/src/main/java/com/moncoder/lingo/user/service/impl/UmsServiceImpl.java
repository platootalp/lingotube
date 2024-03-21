package com.moncoder.lingo.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.mapper.UmsUserMapper;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.service.IUmsUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Service
public class UmsServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Override
    public String sendCode(String phone) {
        return null;
    }

    @Override
    public String register(UserRegisterDTO userRegisterDTO) {
        return null;
    }
}
