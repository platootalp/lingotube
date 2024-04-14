package com.moncoder.lingo.mapper;

import com.moncoder.lingo.entity.VmsVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 视频表 Mapper 接口
 * </p>
 *
 * @author moncoder
 * @since 2024-03-28 15:51:18
 */
public interface VmsVideoMapper extends BaseMapper<VmsVideo> {

    /**
     * 获取最新视频
     * @param videoNums
     * @return
     */
    VmsVideo selectLatestVideos(@Param("num") Integer videoNums);
}
