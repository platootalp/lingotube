package com.moncoder.lingo.admin.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author Moncoder
 * @version 1.0
 * @description 视频上传参数
 * @date 2024/3/30 14:31
 */
@Getter
@Setter
@ApiModel("视频上传参数")
public class VideoCreateDTO {
    @NotBlank
    @ApiModelProperty("视频标题")
    private String title;
    @ApiModelProperty("视频描述")
    private String description;
    @NotBlank
    @ApiModelProperty("视频URL")
    private String videoUrl;
    @NotBlank
    @ApiModelProperty("缩略图URL")
    private String thumbnailUrl;
    @NotNull
    @ApiModelProperty("视频时长（秒）")
    private Integer duration;
    @NotNull
    @ApiModelProperty("视频分类id，外键，关联分类表")
    private Integer categoryId;
    @NotNull
    @ApiModelProperty("视频等级id，外键，关联等级表")
    private Integer levelId;
    @NotNull
    @ApiModelProperty("上传者ID，外键，关联用户表")
    private Integer uploaderId;
}
