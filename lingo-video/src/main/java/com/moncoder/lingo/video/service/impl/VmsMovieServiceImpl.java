package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.entity.VmsMovie;
import com.moncoder.lingo.mapper.VmsMovieMapper;
import com.moncoder.lingo.video.service.IVmsMovieService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电影表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Service
public class VmsMovieServiceImpl extends ServiceImpl<VmsMovieMapper, VmsMovie> implements IVmsMovieService {

}
