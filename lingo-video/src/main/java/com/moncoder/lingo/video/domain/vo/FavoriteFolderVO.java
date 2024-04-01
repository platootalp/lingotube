package com.moncoder.lingo.video.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户收藏夹返回值参数
 * @date 2024/3/28 12:56
 */
@Getter
@Setter
@ApiModel("用户收藏夹返回值参数")
public class FavoriteFolderVO {

    @NotEmpty
    @ApiModelProperty(value = "收藏夹名称")
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "封面图片uri")
    private String coverImage;

    @ApiModelProperty("简介")
    private String description;

    @NotNull
    @ApiModelProperty("是否为公开收藏夹，1为公开，0为私有")
    private Byte isPublic;

}
