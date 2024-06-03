package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.constant.VideoConstant;
import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.entity.VmsCategory;
import com.moncoder.lingo.mapper.VmsCategoryMapper;
import com.moncoder.lingo.video.domain.vo.CategoryVO;
import com.moncoder.lingo.video.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 电影分类表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<VmsCategoryMapper, VmsCategory>
        implements ICategoryService {

    @Autowired
    private IRedisService redisService;

    @Override
    public List<CategoryVO> getAllCategories() {
        // 1.从缓存中获取
        Map<Object, Object> map = redisService.hGetAll(VideoConstant.VMS_VIDEO_CATEGORY_KEY);
        // 转换成List<CategoryVO>类型
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            // 检查value是否为CategoryVO类型
            if (key instanceof String && value instanceof CategoryVO categoryVO) {
                categoryVOList.add(categoryVO);
            } else {
                // 如果value不是CategoryVO类型，可以抛出异常或者采取其他处理措施
                throw new IllegalArgumentException("类型转换异常！");
            }
        }
        // 2.缓存中没有再从数据库中获取
        List<VmsCategory> categories = lambdaQuery().eq(VmsCategory::getIsEnable, (byte) 1).list();
        List<CategoryVO> categoryVOS = categories.stream().map(category -> {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            return categoryVO;
        }).collect(Collectors.toList());
        // 加载到缓存
        Map<String, Object> categoryVOMap = categoryVOS.stream()
                .collect(Collectors.toMap(categoryVO -> String.valueOf(categoryVO.getId())
                        , categoryVO -> categoryVO));
        redisService.hSetAll(VideoConstant.VMS_VIDEO_CATEGORY_KEY, categoryVOMap);
        return categoryVOS;
    }

    @Override
    public CategoryVO getCategoryById(Integer id) {
        // 1. 从缓存中获取
        Object value = redisService.hGet(VideoConstant.VMS_VIDEO_CATEGORY_KEY, String.valueOf(id));
        // 检查value是否为CategoryVO类型
        if (value instanceof CategoryVO categoryVO) {
            return categoryVO;
        } else {
            // 2. 缓存中没有再从数据库中获取
            VmsCategory category = lambdaQuery().eq(VmsCategory::getId, id)
                    .eq(VmsCategory::getIsEnable, (byte) 1)
                    .one();
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            // 将新获取的CategoryVO对象存储回Redis中
            redisService.hSet(VideoConstant.VMS_VIDEO_CATEGORY_KEY, String.valueOf(id), categoryVO);
            return categoryVO;
        }
    }

    @Override
    public String getCategoryName(Integer id) {
        return lambdaQuery().eq(VmsCategory::getId, id)
                .eq(VmsCategory::getIsEnable, (byte) 1)
                .one().getName();
    }


}
