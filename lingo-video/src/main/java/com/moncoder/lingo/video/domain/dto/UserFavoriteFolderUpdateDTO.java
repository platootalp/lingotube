package com.moncoder.lingo.video.domain.dto;

import com.moncoder.lingo.common.annotation.ZeroOrOne;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户收藏夹修改参数
 * @date 2024/3/28 14:19
 */
@Getter
@Setter
@ApiModel("用户收藏夹修改参数")
public class UserFavoriteFolderUpdateDTO {

    @NotBlank
    @ApiModelProperty(value = "收藏夹名称", required = true)
    private String name;
    @ApiModelProperty("封面图片uri")
    private String coverImage;
    @ApiModelProperty("简介")
    private String description;
    @ZeroOrOne
    @ApiModelProperty("是否为公开收藏夹，1为公开，0为私有")
    private Byte isPublic;
}
