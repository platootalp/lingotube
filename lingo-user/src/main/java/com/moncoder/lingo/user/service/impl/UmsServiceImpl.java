package com.moncoder.lingo.user.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.constant.SystemConstant;
import com.moncoder.lingo.common.constant.UserConstant;
import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.common.exception.IllegalParaException;
import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.mapper.UmsUserMapper;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.domain.dto.UserUpdateInfoDTO;
import com.moncoder.lingo.user.domain.vo.UserInfoVO;
import com.moncoder.lingo.user.service.IUmsUserService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // TODO 修改为发送短信
    @Override
    public String sendCode(String phone) {
        // 1.参数校验
        if (StrUtil.isEmpty(phone)) {
            throw new IllegalParaException("手机号不能为空");
        }
        // 2.手机号格式验证
        if (!PhoneUtil.isPhone(phone)) {
            throw new IllegalParaException("手机格式不正确！");
        }
        // 3.60s内不重复发送验证码
        Boolean hasCode = redisService.hasKey(UserConstant.UMS_USER_CODE + phone);
        if (hasCode) {
            throw new ApiException("验证码已发送！");
        }
        // 4.生成6位验证码
        String code = RandomUtil.randomNumbers(6);
        // 5.将验证码存入redis
        redisService.set(UserConstant.UMS_USER_CODE + phone, code, UserConstant.UMS_USER_CODE_EXPIRE);
        return code;
    }

    @Override
    public Boolean register(UserRegisterDTO userRegisterDTO) {
        // 1.参数校验
        if (userRegisterDTO == null) {
            throw new NullPointerException("参数不能为null！");
        }
        // 2.手机号格式验证
        String phone = userRegisterDTO.getPhone();
        if (!PhoneUtil.isPhone(phone)) {
            throw new IllegalParaException("手机格式不正确！");
        }
        // 3.判断电话是否已经被注册
        List<UmsUser> list = lambdaQuery().eq(UmsUser::getPhone, phone).list();
        if (list.size() > 0) {
            throw new ApiException("手机号已经被注册了！");
        }
        // 4.判断验证码是否正确
        String code = userRegisterDTO.getCode();
        log.debug(code);
        String authCode = (String) redisService.get(UserConstant.UMS_USER_CODE + phone);
        if (!code.equals(authCode)) {
            throw new ApiException("验证码错误！");
        }
        // 5.进行注册
        UmsUser umsUser = new UmsUser();
        BeanUtils.copyProperties(userRegisterDTO, umsUser);
        umsUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        umsUser.setGender((byte) 0);
        // 6.获取系统注册用户数量
        String key = SystemConstant.LINGO_USER_COUNT;
        Integer userCount = (Integer) redisService.get(key);// Long会报错
        redisService.incr(key,1L);
        umsUser.setUsername(userCount + UserConstant.UMS_USER_USERNAME_SUFFIX);
        return save(umsUser);
    }

    @Override
    public UserInfoVO getInfo(Integer id) {
        if(id == null){
            return null;
        }
        UmsUser user = lambdaQuery().eq(UmsUser::getId, id).one();
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user,userInfoVO);
        return userInfoVO;
    }

    @Override
    public Boolean updateInfo(UserUpdateInfoDTO userUpdateInfoDTO) {
        if(userUpdateInfoDTO == null){
            throw new NullPointerException("参数不能为null！");
        }
        // 进行修改
        Integer id = userUpdateInfoDTO.getId();
        String nickname = userUpdateInfoDTO.getNickname();
        String introduce = userUpdateInfoDTO.getIntroduce();
        Byte gender = userUpdateInfoDTO.getGender();
        LocalDate birthday = userUpdateInfoDTO.getBirthday();
        String address = userUpdateInfoDTO.getAddress();
        return lambdaUpdate().eq(UmsUser::getId, id)
                .set(StrUtil.isNotEmpty(nickname), UmsUser::getNickname, nickname)
                .set(StrUtil.isNotEmpty(introduce), UmsUser::getIntroduce, introduce)
                .set(gender != null, UmsUser::getGender, gender)
                .set(birthday != null, UmsUser::getBirthday, birthday)
                .set(StrUtil.isNotEmpty(address), UmsUser::getAddress, address)
                .update();
    }

}
