package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsUserFavoriteFolder;
import com.moncoder.lingo.video.domain.dto.FavoriteFolderCreateDTO;
import com.moncoder.lingo.video.domain.dto.FavoriteFolderUpdateDTO;
import com.moncoder.lingo.video.domain.vo.FavoriteFolderVO;

import java.util.List;

/**
 * <p>
 * 用户收藏夹表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
public interface IVmsUserFavoriteFolderService extends IService<VmsUserFavoriteFolder> {

    /**
     * 创建收藏夹
     *
     * @param userFavoriteFolderDTO
     * @return
     */
    boolean create(FavoriteFolderCreateDTO userFavoriteFolderDTO);

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
     * @param userFavoriteFolderUpdateDTO
     * @return
     */
    boolean update(Integer id, FavoriteFolderUpdateDTO userFavoriteFolderUpdateDTO);

    /**
     * 获取指定用户的收藏夹列表
     *
     * @param userId
     * @return
     */
    List<FavoriteFolderVO> getList(Integer userId);

}
