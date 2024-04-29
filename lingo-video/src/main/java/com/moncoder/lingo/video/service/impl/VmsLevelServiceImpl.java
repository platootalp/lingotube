package com.moncoder.lingo.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moncoder.lingo.common.constant.VideoConstant;
import com.moncoder.lingo.common.service.IRedisService;
import com.moncoder.lingo.entity.VmsCategory;
import com.moncoder.lingo.entity.VmsLevel;
import com.moncoder.lingo.mapper.VmsLevelMapper;
import com.moncoder.lingo.video.domain.vo.CategoryVO;
import com.moncoder.lingo.video.domain.vo.LevelVO;
import com.moncoder.lingo.video.service.IVmsLevelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频等级表 服务实现类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 15:08:48
 */
@Service
public class VmsLevelServiceImpl extends ServiceImpl<VmsLevelMapper, VmsLevel> implements IVmsLevelService {

    @Autowired
    private IRedisService redisService;

    @Override
    public List<LevelVO> getAllLevel() {
        // 1.从缓存中获取
        Map<Object, Object> map = redisService.hGetAll(VideoConstant.VMS_VIDEO_LEVEL_KEY);
        // 转换成List<LevelVO>类型
        List<LevelVO> levelVOList = new ArrayList<>();
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            if (key instanceof String && value instanceof LevelVO levelVO) {
                levelVOList.add(levelVO);
            } else {
                throw new IllegalArgumentException("类型转换异常！");
            }
        }
        // 2.缓存中没有再从数据库中获取
        List<VmsLevel> levels = lambdaQuery().eq(VmsLevel::getIsEnable, (byte) 1).list();
        List<LevelVO> levelVOS = levels.stream().map(level -> {
            LevelVO levelVO = new LevelVO();
            BeanUtils.copyProperties(level, levelVO);
            return levelVO;
        }).collect(Collectors.toList());
        // 加载到缓存
        Map<String, Object> levelVOMap = levelVOS.stream()
                .collect(Collectors.toMap(levelVO -> String.valueOf(levelVO.getId())
                        , levelVO -> levelVO));
        redisService.hSetAll(VideoConstant.VMS_VIDEO_LEVEL_KEY, levelVOMap);
        return levelVOS;
    }

    @Override
    public LevelVO getLevelById(Integer id) {
        // 1. 从缓存中获取
        Object value = redisService.hGet(VideoConstant.VMS_VIDEO_LEVEL_KEY, String.valueOf(id));
        // 检查value是否为LevelVO类型
        if (value instanceof LevelVO levelVO) {
            return levelVO;
        } else {
            // 2. 缓存中没有再从数据库中获取
            VmsLevel level = lambdaQuery().eq(VmsLevel::getId, id)
                    .eq(VmsLevel::getIsEnable, (byte) 1)
                    .one();
            LevelVO levelVO = new LevelVO();
            BeanUtils.copyProperties(level, levelVO);
            redisService.hSet(VideoConstant.VMS_VIDEO_LEVEL_KEY, String.valueOf(id), levelVO);
            return levelVO;
        }
    }
}
