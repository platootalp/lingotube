package com.moncoder.lingo.video.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频分类返回值
 * @date 2024/4/26 14:03
 */
@Getter
@Setter
@ApiModel("视频分类返回值")
public class CategoryVO {
    @ApiModelProperty("分类唯一标识id")
    private Integer id;
    @ApiModelProperty("分类名称")
    private String name;
    @ApiModelProperty("分类描述")
    private String description;
}
