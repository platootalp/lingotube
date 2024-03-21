package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsCategory;
import com.moncoder.lingo.mapper.VmsCategoryMapper;
import com.moncoder.lingo.video.service.IVmsCategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电影分类表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Service
public class VmsCategoryServiceImpl extends ServiceImpl<VmsCategoryMapper, VmsCategory> implements IVmsCategoryService {

}
