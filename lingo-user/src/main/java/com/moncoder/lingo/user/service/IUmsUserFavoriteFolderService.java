package com.moncoder.lingo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.UmsUserFavoriteFolder;
import com.moncoder.lingo.user.domain.dto.UserFavoriteFolderDTO;
import com.moncoder.lingo.user.domain.dto.UserFavoriteFolderUpdateDTO;
import com.moncoder.lingo.user.domain.vo.UserFavoriteFolderVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 用户收藏夹表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
public interface IUmsUserFavoriteFolderService extends IService<UmsUserFavoriteFolder> {

    /**
     * 创建收藏夹
     *
     * @param userFavoriteFolderDTO
     * @return
     */
    boolean create(UserFavoriteFolderDTO userFavoriteFolderDTO);

    /**
     * 删除非默认文件夹
     *
     * @param id 非默认文件夹id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 修改收藏夹信息
     *
     * @param id
     * @param userFavoriteFolderDTO
     * @return
     */
    boolean update(Integer id, UserFavoriteFolderUpdateDTO userFavoriteFolderUpdateDTO);

    /**
     * 获取指定用户的收藏夹列表
     *
     * @param userId
     * @return
     */
    List<UserFavoriteFolderVO> getList(Integer userId);

}
