package com.moncoder.lingo.video.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsUserFavoriteFolder;
import com.moncoder.lingo.mapper.VmsUserFavoriteFolderMapper;
import com.moncoder.lingo.video.api.UploadClient;
import com.moncoder.lingo.video.domain.dto.FavoriteFolderCreateDTO;
import com.moncoder.lingo.video.domain.dto.FavoriteFolderUpdateDTO;
import com.moncoder.lingo.video.domain.vo.FavoriteFolderVO;
import com.moncoder.lingo.video.service.IVmsUserFavoriteFolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class VmsUserFavoriteFolderServiceImpl
        extends ServiceImpl<VmsUserFavoriteFolderMapper, VmsUserFavoriteFolder>
        implements IVmsUserFavoriteFolderService {

    @Override
    public boolean create(FavoriteFolderCreateDTO userFavoriteFolderDTO) {
        // 1.判断新创建的文件夹是否为默认文件夹，默认文件夹每个用户只能有一个
        Integer userId = userFavoriteFolderDTO.getUserId();
        Byte isDefault = userFavoriteFolderDTO.getIsDefault();
        if (isDefault.equals((byte) 1)) {
            Long count = lambdaQuery().eq(VmsUserFavoriteFolder::getUserId, userId)
                    .eq(VmsUserFavoriteFolder::getIsDefault, (byte) 1)
                    .count();
            if (count > 0) {
                log.error("默认收藏夹只能有一个！");
                return false;
            }
        }
        // 保存到数据库
        VmsUserFavoriteFolder userFavoriteFolder = new VmsUserFavoriteFolder();
        BeanUtils.copyProperties(userFavoriteFolderDTO, userFavoriteFolder);
        return save(userFavoriteFolder);
    }

    @Override
    public boolean delete(Integer id) {
        VmsUserFavoriteFolder favoriteFolder = getById(id);
        // 默认收藏夹不可删除
        if (favoriteFolder.getIsDefault().equals((byte) 1)) {
            log.error("默认收藏夹不可删除！");
            return false;
        }
        return removeById(id);
    }

    @Override
    public boolean update(Integer id, FavoriteFolderUpdateDTO userFavoriteFolderUpdateDTO) {
        VmsUserFavoriteFolder favoriteFolder = getById(id);
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
        return lambdaUpdate().eq(VmsUserFavoriteFolder::getId, id)
                .set(StrUtil.isNotEmpty(name), VmsUserFavoriteFolder::getName, name)
                .set(StrUtil.isNotEmpty(coverImage), VmsUserFavoriteFolder::getCoverImage, coverImage)
                .set(StrUtil.isNotEmpty(description), VmsUserFavoriteFolder::getDescription, description)
                .set(isPublic != null, VmsUserFavoriteFolder::getIsPublic, isPublic)
                .update();
    }


    @Override
    public List<FavoriteFolderVO> getList(Integer userId) {
        // 获取指定用户的收藏夹列表
        return lambdaQuery().eq(VmsUserFavoriteFolder::getUserId, userId).list()
                .stream().map(item -> {
                    FavoriteFolderVO userFavoriteFolderVO = new FavoriteFolderVO();
                    BeanUtils.copyProperties(item, userFavoriteFolderVO);
                    return userFavoriteFolderVO;
                }).collect(Collectors.toList());

    }
}
