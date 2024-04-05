package com.moncoder.lingo.user.service.impl;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.api.domain.UserCommentInfoVO;
import com.moncoder.lingo.common.constant.SystemConstant;
import com.moncoder.lingo.common.constant.UserConstant;
import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.common.exception.FileUploadException;
import com.moncoder.lingo.common.exception.IllegalArgumentException;
import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.common.util.FileUtil;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.mapper.UmsUserMapper;
import com.moncoder.lingo.user.domain.dto.UserRegisterDTO;
import com.moncoder.lingo.user.domain.dto.UserInfoUpdateDTO;
import com.moncoder.lingo.user.domain.vo.UserInfoVO;
import com.moncoder.lingo.user.service.IUmsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private Environment environment;

    /**
     * TODO 修改为发送短信
     *
     * @param phone
     * @return
     */
    @Override
    public String sendCode(String phone) {
        // 1.参数校验
        if (StrUtil.isEmpty(phone)) {
            throw new IllegalArgumentException("手机号不能为空");
        }
        // 2.手机号格式验证
        if (!PhoneUtil.isPhone(phone)) {
            throw new IllegalArgumentException("手机格式不正确！");
        }
        // 3.5min内不重复发送验证码
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
    public boolean register(UserRegisterDTO userRegisterDTO) {
        // 1.参数校验
        if (userRegisterDTO == null) {
            throw new NullPointerException("参数不能为null！");
        }
        // 2.手机号格式验证
        String phone = userRegisterDTO.getPhone();
        if (!PhoneUtil.isPhone(phone)) {
            throw new IllegalArgumentException("手机格式不正确！");
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
        redisService.incr(key, 1L);
        umsUser.setUsername(userCount + UserConstant.UMS_USER_USERNAME_SUFFIX);
        return save(umsUser);
    }

    @Override
    public UserInfoVO getInfo(Integer id) {
        if (id == null) {
            return null;
        }
        UmsUser user = lambdaQuery().eq(UmsUser::getId, id).one();
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        return userInfoVO;
    }

    @Override
    public boolean updateInfo(UserInfoUpdateDTO userUpdateInfoDTO) {
        if (userUpdateInfoDTO == null) {
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

    @Override
    public boolean verifyCode(String phone, String code) {
        String authCode = (String) redisService.get(UserConstant.UMS_USER_CODE + phone);
        return code.equals(authCode);
    }

    @Override
    public boolean updatePassword(String phone, String password) {
        return lambdaUpdate().eq(StrUtil.isNotEmpty(phone), UmsUser::getPhone, phone)
                .set(StrUtil.isNotEmpty(password), UmsUser::getPassword, passwordEncoder.encode(password))
                .set(UmsUser::getUpdatedTime, LocalDateTime.now())
                .update();
    }

    @Override
    public boolean updateAvatar(Integer id, MultipartFile file) {
        // 上传头像并获取uri
        String avatar = null;
        try {
            avatar = FileUtil.saveFile(file, UserConstant.UMS_USER_AVATAR_PATH);
        } catch (IOException e) {
            throw new FileUploadException("文件上传失败！");
        }
        // 设置用户头像uri
        UmsUser umsUser = new UmsUser();
        umsUser.setId(id);
        umsUser.setAvatar(avatar);
        return updateById(umsUser);
    }

    @Override
    public String getAvatar(Integer id) {
        String avatarPath = getById(id).getAvatar();
        // 获取服务器 IP 地址
        String ipAddress = environment.getProperty("server.address");
        // 获取服务器端口号
        int port = environment.getProperty("server.port", Integer.class);
        // 构建URL
        return "http://" + ipAddress + ":" + port + "/" + avatarPath;
    }

    @Override
    public UserCommentInfoVO getCommentInfo(Integer id) {
        UmsUser user = lambdaQuery().eq(UmsUser::getId, id)
                .one();
        UserCommentInfoVO userCommentInfoVO = new UserCommentInfoVO();
        BeanUtils.copyProperties(user, userCommentInfoVO);
        return userCommentInfoVO;
    }

}
