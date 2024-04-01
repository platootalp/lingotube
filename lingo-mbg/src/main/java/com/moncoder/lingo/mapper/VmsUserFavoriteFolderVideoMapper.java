package com.moncoder.lingo.mapper;

import com.moncoder.lingo.entity.VmsUserFavoriteFolderVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户收藏视频记录表 Mapper 接口
 * </p>
 *
 * @author moncoder
 * @since 2024-04-01 13:42:53
 */
public interface VmsUserFavoriteFolderVideoMapper extends BaseMapper<VmsUserFavoriteFolderVideo> {

    List<VmsUserFavoriteFolderVideo> selectBatchByIds(@Param("ids") List<Integer> ids);

}
