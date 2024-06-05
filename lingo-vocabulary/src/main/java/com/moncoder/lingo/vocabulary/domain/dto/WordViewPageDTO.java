package com.moncoder.lingo.vocabulary.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Moncoder
 * @version 1.0
 * @description 单词分页查询参数
 * @date 2024/6/4 17:07
 */
@Data
@ApiModel("单词分页查询参数")
public class WordViewPageDTO {

    @Min(value = 1,message = "当前页号必须大于0")
    @NotNull(message = "当前页号不能为空")
    @ApiModelProperty("当前页号")
    private Integer currentPage;

    @Min(value = 1,message = "每页条数必须大于0")
    @NotNull(message = "每页条数不能为空")
    @ApiModelProperty("每页条数")
    private Integer pageSize;

    @ApiModelProperty("关键字")
    private String keyword;

    @NotNull(message = "排序规则不能为空")
    @ApiModelProperty("排序规则")
    private Integer sortBy;
}
