package com.moncoder.lingo.mapper;

import com.moncoder.lingo.entity.VmsVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
     *
     * @param videoNums
     * @return
     */
    List<VmsVideo> selectLatestVideos(@Param("num") Integer videoNums);

    /**
     * 获取最热视频
     *
     * @param videoNums
     * @return
     */
    List<VmsVideo> selectTrendingVideos(@Param("num") Integer videoNums);


    /**
     * 获取推荐视频
     *
     * @param videoNums
     * @return
     */
    List<VmsVideo> selectRecommendedVideos(@Param("num") Integer videoNums);

    /**
     * 获取相关视频
     *
     * @param id
     * @param levelName
     * @return
     */
    @Select("SELECT * FROM vms_video WHERE is_enable = 1 AND id != #{id} AND level_name = #{levelName} LIMIT #{num}")
    List<VmsVideo> selectRelatedVideos(@Param("id") Integer id,
                                       @Param("levelName") String levelName,
                                       @Param("num") Integer num);
}


