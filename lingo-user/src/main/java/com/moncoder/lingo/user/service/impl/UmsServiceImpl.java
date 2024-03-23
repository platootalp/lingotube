package com.moncoder.lingo.user.service.impl;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.constant.UserConstant;
import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.mapper.UmsUserMapper;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.service.IUmsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Slf4j
@Service
public class UmsServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String sendCode(String phone) {
        return null;
    }

    @Override
    public Boolean register(UserRegisterDTO userRegisterDTO) {
        // 1.参数校验
        if(userRegisterDTO == null){
            return false;
        }
        // 2.手机号格式验证
        String phone = userRegisterDTO.getPhone();
        if(!PhoneUtil.isPhone(phone)){
        }
        // 3.判断电话是否已经被注册
        List<UmsUser> list = lambdaQuery().eq(UmsUser::getPhone, phone).list();
        if(list.size() > 0){
            return false;
        }
        // 4.判断验证码是否正确
        String code = userRegisterDTO.getCode();
        String authCode = (String)redisService.get(UserConstant.UMS_USER_CODE + phone);
        if(!code.equals(authCode)){
            return false;
        }
        // 5.进行注册
        UmsUser umsUser = new UmsUser();
        BeanUtils.copyProperties(userRegisterDTO,umsUser);
        umsUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        umsUser.setGender((byte) 0);
        umsUser.setNickname("linger");
        return save(umsUser);
    }
}
