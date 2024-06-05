package com.moncoder.lingo.vocabulary.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moncoder.lingo.common.api.LPage;
import com.moncoder.lingo.vocabulary.domain.vo.WordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * @description
 * @date 2024/6/5 13:54
 */
public interface WordDao {

    /**
     * 查询单词列表
     * @param keyword
     * @param sortBy
     * @return
     */
    List<WordVO> selectWordList(@Param("keyword") String keyword,
                                @Param("sortBy") Integer sortBy);
}
