package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsCategory;
import com.moncoder.lingo.video.domain.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 * 电影分类表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
public interface IVmsCategoryService extends IService<VmsCategory> {

    /**
     * 获取全部分类
     *
     * @return
     */
    List<CategoryVO> getAllCategories();

    /**
     * 根据id获取分类
     *
     * @param id
     * @return
     */
    CategoryVO getCategoryById(Integer id);

    /**
     * 根据id获取分类名
     *
     * @param id
     * @return
     */
    String getCategoryName(Integer id);
}
