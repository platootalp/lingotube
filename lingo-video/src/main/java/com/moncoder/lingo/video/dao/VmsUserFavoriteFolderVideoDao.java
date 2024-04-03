package com.moncoder.lingo.video.dao;

import com.moncoder.lingo.video.domain.vo.FavoriteFolderVideoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * @description 自定义映射
 * @date 2024/4/3 11:39
 */
public interface VmsUserFavoriteFolderVideoDao {

    List<FavoriteFolderVideoVO> selectAllByVideoIds(@Param("videoIds") List<Integer> videoIds,
                                                    @Param("titleKeyWord") String titleKeyWord);
}
