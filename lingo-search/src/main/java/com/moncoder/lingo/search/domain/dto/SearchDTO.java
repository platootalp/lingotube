package com.moncoder.lingo.search.domain.dto;

import lombok.Data;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO 搜索参数
 * @date 2024/5/14 15:37
 */
@Data
public class SearchDTO {
    String keyWord;
    String levelName;
    String categoryName;
    Integer sortBy;
    Integer pageNum;
    Integer pageSize;
}
