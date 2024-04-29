package com.moncoder.lingo.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moncoder.lingo.entity.VmsLevel;
import com.moncoder.lingo.video.domain.vo.LevelVO;

import java.util.List;

/**
 * <p>
 * 视频等级表 服务类
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 15:08:48
 */
public interface IVmsLevelService extends IService<VmsLevel> {

    /**
     * 获取全部视频等级
     *
     * @return
     */
    List<LevelVO> getAllLevel();

    /**
     * 根据id获取等级
     *
     * @param id
     * @return
     */
    LevelVO getLevelById(Integer id);
}
