package com.moncoder.lingo.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.constant.UserConstant;
import com.moncoder.lingo.common.exception.FileUploadException;
import com.moncoder.lingo.common.exception.IllegalArgumentException;
import com.moncoder.lingo.common.util.FileUtil;
import com.moncoder.lingo.entity.UmsUser;
import com.moncoder.lingo.entity.UmsUserFavoriteFolder;
import com.moncoder.lingo.mapper.UmsUserFavoriteFolderMapper;
import com.moncoder.lingo.user.domain.dto.UserFavoriteFolderDTO;
import com.moncoder.lingo.user.domain.dto.UserFavoriteFolderUpdateDTO;
import com.moncoder.lingo.user.domain.vo.UserFavoriteFolderVO;
import com.moncoder.lingo.user.service.IUmsUserFavoriteFolderService;
import com.moncoder.lingo.user.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户收藏夹表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Service
@Slf4j
public class UmsUserFavoriteFolderServiceImpl extends ServiceImpl<UmsUserFavoriteFolderMapper, UmsUserFavoriteFolder> implements IUmsUserFavoriteFolderService {

    @Autowired
    private IUploadService uploadService;

    @Override
    public boolean create(UserFavoriteFolderDTO userFavoriteFolderDTO) {
        // 1.判断新创建的文件夹是否为默认文件夹，默认文件夹每个用户只能有一个
        Integer userId = userFavoriteFolderDTO.getUserId();
        Byte isDefault = userFavoriteFolderDTO.getIsDefault();
        if (isDefault.equals((byte) 1)) {
            Long count = lambdaQuery().eq(UmsUserFavoriteFolder::getUserId, userId)
                    .eq(UmsUserFavoriteFolder::getIsDefault, (byte) 1)
                    .count();
            if (count > 0) {
                log.error("默认收藏夹只能有一个！");
                return false;
            }
        }
        // 保存到数据库
        UmsUserFavoriteFolder userFavoriteFolder = new UmsUserFavoriteFolder();
        BeanUtils.copyProperties(userFavoriteFolderDTO, userFavoriteFolder);
        return save(userFavoriteFolder);
    }

    @Override
    public boolean delete(Integer id) {
        UmsUserFavoriteFolder favoriteFolder = getById(id);
        // 默认收藏夹不可删除
        if (favoriteFolder.getIsDefault().equals((byte) 1)) {
            log.error("默认收藏夹不可删除！");
            return false;
        }
        return removeById(id);
    }

    @Override
    public boolean update(Integer id, UserFavoriteFolderUpdateDTO userFavoriteFolderUpdateDTO) {
        UmsUserFavoriteFolder favoriteFolder = getById(id);
        // 默认收藏夹只能修改名称和公开性
        if (favoriteFolder.getIsDefault().equals((byte) 1)) {
            favoriteFolder.setName(userFavoriteFolderUpdateDTO.getName());
            favoriteFolder.setIsPublic(userFavoriteFolderUpdateDTO.getIsPublic());
            return updateById(favoriteFolder);
        }
        // 非默认收藏夹更新
        String name = userFavoriteFolderUpdateDTO.getName();
        String coverImage = userFavoriteFolderUpdateDTO.getCoverImage();
        String description = userFavoriteFolderUpdateDTO.getDescription();
        Byte isPublic = userFavoriteFolderUpdateDTO.getIsPublic();
        return lambdaUpdate().eq(UmsUserFavoriteFolder::getId, id)
                .set(StrUtil.isNotEmpty(name), UmsUserFavoriteFolder::getName, name)
                .set(StrUtil.isNotEmpty(coverImage), UmsUserFavoriteFolder::getCoverImage, coverImage)
                .set(StrUtil.isNotEmpty(description), UmsUserFavoriteFolder::getDescription, description)
                .set(isPublic != null, UmsUserFavoriteFolder::getIsPublic, isPublic)
                .update();
    }


    @Override
    public List<UserFavoriteFolderVO> getList(Integer userId) {
        // 获取指定用户的收藏夹列表
        return lambdaQuery().eq(UmsUserFavoriteFolder::getUserId, userId).list()
                .stream().map(item -> {
                    UserFavoriteFolderVO userFavoriteFolderVO = new UserFavoriteFolderVO();
                    BeanUtils.copyProperties(item, userFavoriteFolderVO);
                    return userFavoriteFolderVO;
                }).collect(Collectors.toList());

    }
}
